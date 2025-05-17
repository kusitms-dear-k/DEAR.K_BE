package com.deark.be.event.service;

import static com.deark.be.event.exception.errorcode.EventErrorCode.EVENT_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.NO_PERMISSION;
import static com.deark.be.event.exception.errorcode.EventStoreErrorCode.EVENT_STORE_NOT_FOUND;
import static com.deark.be.store.exception.errorcode.StoreErrorCode.STORE_NOT_FOUND;

import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventStore;
import com.deark.be.event.domain.type.ThumbnailSource;
import com.deark.be.event.dto.request.UpdateStoreMappingRequest;
import com.deark.be.event.exception.EventException;
import com.deark.be.event.repository.EventRepository;
import com.deark.be.event.repository.EventStoreRepository;
import com.deark.be.store.domain.Store;
import com.deark.be.store.exception.StoreException;
import com.deark.be.store.repository.StoreRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventStoreService {
    private final EventStoreRepository eventStoreRepository;
    private final EventRepository eventRepository;
    private final StoreRepository storeRepository;
    private final EventService eventService;

    @Transactional
    public void updateStoreEventMappings(UpdateStoreMappingRequest request, Long userId) {
        Store store = getStoreOrThrow(request.storeId());
        List<EventStore> currentMappings = getUserOwnedStoreMappings(store, userId);

        Set<Long> currentEventIds = currentMappings.stream()
                .map(ed -> ed.getEvent().getId())
                .collect(Collectors.toSet());
        Set<Long> requestedEventIds = new HashSet<>(request.eventIds());

        removeObsoleteStoreMappings(currentMappings, requestedEventIds, store);
        addNewStoreMappings(requestedEventIds, currentEventIds, store, userId);
    }

    private Store getStoreOrThrow(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
    }

    // 현재 로그인한 사용자가 소유한 해당 스토어의 EventStore 매핑 목록을 가져옴
    private List<EventStore> getUserOwnedStoreMappings(Store store, Long userId) {
        return eventStoreRepository.findAllByStore(store).stream()
                .filter(es -> es.getEvent().getUser().getId().equals(userId))
                .toList();
    }

    // 기존 매핑 중 요청된 이벤트에 포함되지 않은 매핑들을 제거하고 썸네일도 필요한 경우 갱신
    private void removeObsoleteStoreMappings(List<EventStore> existingMappings, Set<Long> requestedEventIds, Store store) {
        for (EventStore mapping : existingMappings) {
            Event event = mapping.getEvent();
            Long eventId = event.getId();

            if (!requestedEventIds.contains(eventId)) {
                event.removeEventStore(mapping);

                if (event.isThumbnailFromStore(store.getId())) {
                    eventService.resolveAndUpdateThumbnail(event);
                }
            }
        }
    }

    // 요청된 이벤트 중 현재 매핑이 없는 이벤트에 대해 새 매핑을 추가하고 썸네일도 최초 설정
    private void addNewStoreMappings(Set<Long> requestedEventIds, Set<Long> currentEventIds, Store store, Long userId) {
        Set<Long> toAdd = new HashSet<>(requestedEventIds);
        toAdd.removeAll(currentEventIds);

        for (Long eventId : toAdd) {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

            if (!event.getUser().getId().equals(userId)) {
                throw new EventException(NO_PERMISSION);
            }

            EventStore newMapping = EventStore.builder()
                    .store(store)
                    .event(event)
                    .build();

            event.addEventStore(newMapping);
            eventStoreRepository.save(newMapping);

            // 썸네일이 비어있으면 설정
            event.updateThumbnailIfAbsent(store.getImageUrl(), ThumbnailSource.STORE, store.getId());
        }
    }

    @Transactional
    public void removeStoreFromEvent(Long eventId, Long storeId, Long userId) {
        Event event=eventService.getValidatedEvent(eventId,userId);

        EventStore eventStore = eventStoreRepository.findByEventIdAndStoreId(eventId, storeId)
                .orElseThrow(() -> new EventException(EVENT_STORE_NOT_FOUND));

        event.getEventStoreList().remove(eventStore);

        // 썸네일이 해당 스토어에서 왔다면 갱신 필요
        if (event.isThumbnailFromStore(storeId)) {
            eventService.resolveAndUpdateThumbnail(event);
        }
    }

}