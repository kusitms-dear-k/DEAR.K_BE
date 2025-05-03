package com.deark.be.event.service;

import static com.deark.be.event.exception.errorcode.EventErrorCode.EVENT_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.NO_PERMISSION;
import static com.deark.be.user.exception.errorcode.UserErrorCode.USER_NOT_FOUND;

import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventDesign;
import com.deark.be.event.domain.EventStore;
import com.deark.be.event.dto.request.EventCreateRequest;
import com.deark.be.event.dto.request.EventUpdateRequest;
import com.deark.be.event.dto.response.DesignInEventResponse;
import com.deark.be.event.dto.response.EventResponse;
import com.deark.be.event.dto.response.EventWithCheckResponse;
import com.deark.be.event.dto.response.StoreInEventResponse;
import com.deark.be.event.exception.EventException;
import com.deark.be.event.repository.EventDesignRepository;
import com.deark.be.event.repository.EventRepository;
import com.deark.be.event.repository.EventStoreRepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.exception.UserException;
import com.deark.be.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventDesignRepository eventDesignRepository;
    private final EventStoreRepository eventStoreRepository;

    public List<EventResponse> getMyEvents(Long userId) {
        User user=getValidatedUser(userId);
        List<Event> events = user.getEventList();
        return events.stream()
                .map(event ->{
                    String thumnailUrl=resolveThumbnail(event);
                    return EventResponse.from(event, thumnailUrl);
                })
                .collect(Collectors.toList());
    }

    public EventResponse getEventDetail(Long eventId, Long userId) {
        Event event = getValidatedEvent(eventId,userId);
        return EventResponse.from(event,resolveThumbnail(event));
    }

    @Transactional
    public Long createEvent(Long userId,EventCreateRequest request) {
        User user=getValidatedUser(userId);
        Event event=request.toEntity(user);
        return eventRepository.save(event).getId();
    }

    @Transactional
    public void deleteEvent(Long eventId, Long userId) {
        Event event = getValidatedEvent(eventId,userId);
        eventRepository.delete(event);
    }

    @Transactional
    public void updateEvent(Long eventId, Long userId, EventUpdateRequest request) {
        Event event = getValidatedEvent(eventId,userId);
        request.updateEvent(event);
    }

    public List<DesignInEventResponse> getDesignsInEvent(Long eventId, Long userId) {
        Event event = getValidatedEvent(eventId, userId);

        return event.getEventDesignList().stream()
                .map(DesignInEventResponse::from)
                .collect(Collectors.toList());
    }

    public List<StoreInEventResponse> getStoresInEvent(Long eventId, Long userId) {
        Event event = getValidatedEvent(eventId, userId);

        return event.getEventStoreList().stream()
                .map(StoreInEventResponse::from)
                .collect(Collectors.toList());
    }

    public String resolveThumbnail(Event event) {
        Optional<EventDesign> firstDesignOpt = eventDesignRepository
                .findTopByEventIdOrderByCreatedAtAsc(event.getId());

        Optional<EventStore> firstStoreOpt = eventStoreRepository
                .findTopByEventIdOrderByCreatedAtAsc(event.getId());

        // 둘 다 존재하면 비교
        if (firstDesignOpt.isPresent() && firstStoreOpt.isPresent()) {
            EventDesign design = firstDesignOpt.get();
            EventStore store = firstStoreOpt.get();

            return design.getCreatedAt().isBefore(store.getCreatedAt())
                    ? design.getDesign().getImageUrl()
                    : store.getStore().getImageUrl();
        }

        // 하나만 존재하는 경우
        if (firstDesignOpt.isPresent()) {
            return firstDesignOpt.get().getDesign().getImageUrl();
        }

        if (firstStoreOpt.isPresent()) {
            return firstStoreOpt.get().getStore().getImageUrl();
        }

        // 아무것도 없으면 기본 이미지 또는 null
        return null;
    }



    public List<EventWithCheckResponse> getMyEventsWithCheckForDesign(Long userId, Long designId) {
        User user=getValidatedUser(userId);

        List<Event> events = user.getEventList();

        return events.stream()
                .map(event -> {
                    boolean isChecked = eventDesignRepository.existsByEventIdAndDesignId(event.getId(), designId);
                    String thumbnailUrl = resolveThumbnail(event);
                    return EventWithCheckResponse.from(event, thumbnailUrl, isChecked);
                })
                .collect(Collectors.toList());
    }

    public List<EventWithCheckResponse> getMyEventsWithCheckForStore(Long userId, Long storeId) {
        User user=getValidatedUser(userId);
        List<Event> events = user.getEventList();

        return events.stream()
                .map(event -> {
                    boolean isChecked = eventStoreRepository.existsByEventIdAndStoreId(event.getId(), storeId);
                    String thumbnailUrl = resolveThumbnail(event);
                    return EventWithCheckResponse.from(event, thumbnailUrl, isChecked);
                })
                .collect(Collectors.toList());
    }

    private Event getValidatedEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));

        if (!event.getUser().getId().equals(userId)) {
            throw new EventException(NO_PERMISSION);
        }

        return event;
    }

    private User getValidatedUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }
}
