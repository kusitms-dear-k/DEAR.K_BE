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
import com.deark.be.event.dto.request.UpdateDesignMappingRequest;
import com.deark.be.event.exception.EventException;
import com.deark.be.event.repository.EventDesignRepository;
import com.deark.be.event.repository.EventRepository;
import java.util.List;
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

    @Transactional
    public void updateDesignEventMappings(UpdateDesignMappingRequest request) {
        Design design = designRepository.findById(request.designId())
                .orElseThrow(() -> new DesignException(DESIGN_NOT_FOUND));

        // 1. 기존 매핑 삭제
        List<EventDesign> existingMappings = eventDesignRepository.findAllByDesign(design);
        for (EventDesign mapping : existingMappings) {
            Event event = mapping.getEvent();
            event.removeEventDesign(mapping);
        }

        // 2. 새로운 매핑 추가
        for (Long eventId : request.eventIds()) {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

            EventDesign newMapping = EventDesign.builder()
                    .design(design)
                    .event(event)
                    .build();

            event.addEventDesign(newMapping);
            eventDesignRepository.save(newMapping);
        }
    }

    @Transactional
    public void removeDesignFromEvent(Long eventId, Long designId, Long userId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

        // 소유자 확인
        if (!event.getUser().getId().equals(userId)) {
            throw new EventException(NO_PERMISSION);
        }

        EventDesign eventDesign = eventDesignRepository.findByEventIdAndDesignId(eventId, designId)
                .orElseThrow(() -> new EventException(EVENT_DESIGN_NOT_FOUND));

        event.getEventDesignList().remove(eventDesign);
    }
}
