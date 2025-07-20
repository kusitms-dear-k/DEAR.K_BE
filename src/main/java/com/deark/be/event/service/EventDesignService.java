package com.deark.be.event.service;

import static com.deark.be.design.exception.errorcode.DesignErrorCode.DESIGN_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventDesignErrorCode.EVENT_DESIGN_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.EVENT_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.NO_PERMISSION;

import com.deark.be.design.domain.CakeDesign;
import com.deark.be.design.exception.DesignException;
import com.deark.be.design.repository.CakeCakeDesignRepository;
import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventDesign;
import com.deark.be.event.domain.type.ThumbnailSource;
import com.deark.be.event.dto.request.UpdateDesignMappingRequest;
import com.deark.be.event.exception.EventException;
import com.deark.be.event.repository.EventDesignRepository;
import com.deark.be.event.repository.EventRepository;
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
public class EventDesignService {

    private final EventDesignRepository eventDesignRepository;
    private final EventRepository eventRepository;
    private final CakeCakeDesignRepository cakeDesignRepository;
    private final EventService eventService;

    @Transactional
    public void updateDesignEventMappings(UpdateDesignMappingRequest request, Long userId) {
        CakeDesign cakeDesign = getDesignOrThrow(request.designId());
        List<EventDesign> currentMappings = getUserOwnedDesignMappings(cakeDesign, userId);

        Set<Long> currentEventIds = currentMappings.stream()
                .map(ed -> ed.getEvent().getId())
                .collect(Collectors.toSet());
        Set<Long> requestedEventIds = new HashSet<>(request.eventIds());

        removeObsoleteDesignMappings(currentMappings, requestedEventIds, cakeDesign);
        addNewDesignMappings(requestedEventIds, currentEventIds, cakeDesign, userId);
    }

    private CakeDesign getDesignOrThrow(Long designId) {
        return cakeDesignRepository.findById(designId)
                .orElseThrow(() -> new DesignException(DESIGN_NOT_FOUND));
    }

    // 현재 로그인한 사용자가 소유한 해당 디자인의 EventDesign 매핑 목록을 가져옴
    private List<EventDesign> getUserOwnedDesignMappings(CakeDesign cakeDesign, Long userId) {
        return eventDesignRepository.findAllByDesign(cakeDesign).stream()
                .filter(ed -> ed.getEvent().getUser().getId().equals(userId))
                .toList();
    }

    // 기존 매핑 중 요청된 이벤트에 포함되지 않은 매핑들을 제거하고 썸네일도 필요한 경우 갱신
    private void removeObsoleteDesignMappings(List<EventDesign> existingMappings, Set<Long> requestedEventIds, CakeDesign cakeDesign) {
        for (EventDesign mapping : existingMappings) {
            Event event = mapping.getEvent();
            Long eventId = event.getId();
            if (!requestedEventIds.contains(eventId)) {
                event.removeEventDesign(mapping);
                if (event.isThumbnailFromDesign(cakeDesign.getId())) {
                    eventService.resolveAndUpdateThumbnail(event);
                }
            }
        }
    }

    // 요청된 이벤트 중 현재 매핑이 없는 이벤트에 대해 새 매핑을 추가하고 썸네일도 최초 설정
    private void addNewDesignMappings(Set<Long> requestedEventIds, Set<Long> currentEventIds, CakeDesign cakeDesign, Long userId) {
        Set<Long> toAdd = new HashSet<>(requestedEventIds);
        toAdd.removeAll(currentEventIds);

        for (Long eventId : toAdd) {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

            if (!event.getUser().getId().equals(userId)) {
                throw new EventException(NO_PERMISSION);
            }

            EventDesign newMapping = EventDesign.builder()
                    .cakeDesign(cakeDesign)
                    .event(event)
                    .build();

            event.addEventDesign(newMapping);
            eventDesignRepository.save(newMapping);
            event.updateThumbnailIfAbsent(cakeDesign.getImageUrl(), ThumbnailSource.DESIGN, cakeDesign.getId());
        }
    }

    @Transactional
    public void removeDesignFromEvent(Long eventId, Long designId, Long userId) {

        Event event = eventService.getValidatedEvent(eventId,userId);

        EventDesign eventDesign = eventDesignRepository.findByEventIdAndDesignId(eventId, designId)
                .orElseThrow(() -> new EventException(EVENT_DESIGN_NOT_FOUND));

        event.getEventDesignList().remove(eventDesign);

        // 썸네일이 해당 디자인에서 왔다면 갱신 필요
        if (event.isThumbnailFromDesign(designId)) {
            eventService.resolveAndUpdateThumbnail(event);
        }
    }

    @Transactional
    public void updateMemo(Long eventId, Long designId, Long userId, String memo){
        eventService.getValidatedEvent(eventId, userId);
        EventDesign eventDesign = eventDesignRepository.findByEventIdAndDesignId(eventId, designId)
                .orElseThrow(() -> new EventException(EVENT_DESIGN_NOT_FOUND));
        eventDesign.updateMemo(memo);
    }

}
