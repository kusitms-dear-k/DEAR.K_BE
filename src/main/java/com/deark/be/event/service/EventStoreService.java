package com.deark.be.event.service;

import static com.deark.be.event.exception.errorcode.EventDesignErrorCode.EVENT_DESIGN_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.EVENT_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.NO_PERMISSION;
import static com.deark.be.event.exception.errorcode.EventStoreErrorCode.EVENT_STORE_NOT_FOUND;
import static com.deark.be.store.exception.errorcode.StoreErrorCode.STORE_NOT_FOUND;

import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventStore;
import com.deark.be.event.dto.request.UpdateStoreMappingRequest;
import com.deark.be.event.exception.EventException;
import com.deark.be.event.repository.EventRepository;
import com.deark.be.event.repository.EventStoreRepository;
import com.deark.be.store.domain.Store;
import com.deark.be.store.exception.StoreException;
import com.deark.be.store.repository.StoreRepository;
import java.util.List;
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


    @Transactional
    public void updateStoreEventMappings(UpdateStoreMappingRequest request) {
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        // 1. 기존 매핑 삭제
        List<EventStore> existingMappings = eventStoreRepository.findAllByStore(store);
        for (EventStore mapping : existingMappings) {
            Event event = mapping.getEvent();
            event.removeEventStore(mapping);
        }

        // 2. 새로운 매핑 추가
        for (Long eventId : request.eventIds()) {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

            EventStore newMapping = EventStore.builder()
                    .store(store)
                    .event(event)
                    .build();

            event.addEventStore(newMapping);
            eventStoreRepository.save(newMapping);
        }
    }

    @Transactional
    public void removeStoreFromEvent(Long eventId, Long storeId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

        // 소유자 확인
        if (!event.getUser().getId().equals(userId)) {
            throw new EventException(NO_PERMISSION);
        }

        EventStore eventStore = eventStoreRepository.findByEventIdAndStoreId(eventId, storeId)
                .orElseThrow(() -> new EventException(EVENT_STORE_NOT_FOUND));

        event.getEventStoreList().remove(eventStore);
    }
}
