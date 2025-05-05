package com.deark.be.event.service;

import static com.deark.be.design.exception.errorcode.DesignErrorCode.DESIGN_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventDesignErrorCode.EVENT_DESIGN_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.EVENT_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.NO_PERMISSION;

import com.deark.be.design.domain.Design;
import com.deark.be.design.exception.DesignException;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventDesign;
import com.deark.be.event.domain.ThumbnailSource;
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
    private final DesignRepository designRepository;
    private final EventService eventService;


    @Transactional
    public void updateDesignEventMappings(UpdateDesignMappingRequest request, Long userId) {
        Design design = getDesignOrThrow(request.designId());
        List<EventDesign> currentMappings = getUserOwnedDesignMappings(design, userId);

        Set<Long> currentEventIds = currentMappings.stream()
                .map(ed -> ed.getEvent().getId())
                .collect(Collectors.toSet());
        Set<Long> requestedEventIds = new HashSet<>(request.eventIds());

        removeObsoleteDesignMappings(currentMappings, requestedEventIds, design);
        addNewDesignMappings(requestedEventIds, currentEventIds, design, userId);
    }



    private Design getDesignOrThrow(Long designId) {
        return designRepository.findById(designId)
                .orElseThrow(() -> new DesignException(DESIGN_NOT_FOUND));
    }

    // 현재 로그인한 사용자가 소유한 해당 디자인의 EventDesign 매핑 목록을 가져옴
    private List<EventDesign> getUserOwnedDesignMappings(Design design, Long userId) {
        return eventDesignRepository.findAllByDesign(design).stream()
                .filter(ed -> ed.getEvent().getUser().getId().equals(userId))
                .toList();
    }

    // 기존 매핑 중 요청된 이벤트에 포함되지 않은 매핑들을 제거하고 썸네일도 필요한 경우 갱신
    private void removeObsoleteDesignMappings(List<EventDesign> existingMappings, Set<Long> requestedEventIds, Design design) {
        for (EventDesign mapping : existingMappings) {
            Event event = mapping.getEvent();
            Long eventId = event.getId();
            if (!requestedEventIds.contains(eventId)) {
                event.removeEventDesign(mapping);
                if (event.isThumbnailFromDesign(design.getId())) {
                    eventService.resolveAndUpdateThumbnail(event);
                }
            }
        }
    }

    // 요청된 이벤트 중 현재 매핑이 없는 이벤트에 대해 새 매핑을 추가하고 썸네일도 최초 설정
    private void addNewDesignMappings(Set<Long> requestedEventIds, Set<Long> currentEventIds, Design design, Long userId) {
        Set<Long> toAdd = new HashSet<>(requestedEventIds);
        toAdd.removeAll(currentEventIds);

        for (Long eventId : toAdd) {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

            if (!event.getUser().getId().equals(userId)) {
                throw new EventException(NO_PERMISSION);
            }

            EventDesign newMapping = EventDesign.builder()
                    .design(design)
                    .event(event)
                    .build();

            event.addEventDesign(newMapping);
            eventDesignRepository.save(newMapping);
            event.updateThumbnailIfAbsent(design.getImageUrl(), ThumbnailSource.DESIGN, design.getId());
        }
    }

    @Transactional
    public void removeDesignFromEvent(Long eventId, Long designId, Long userId) {

        Event event=eventService.getValidatedEvent(eventId,userId);

        EventDesign eventDesign = eventDesignRepository.findByEventIdAndDesignId(eventId, designId)
                .orElseThrow(() -> new EventException(EVENT_DESIGN_NOT_FOUND));

        event.getEventDesignList().remove(eventDesign);

        // 썸네일이 해당 디자인에서 왔다면 갱신 필요
        if (event.isThumbnailFromDesign(designId)) {
            eventService.resolveAndUpdateThumbnail(event);
        }
    }

}
