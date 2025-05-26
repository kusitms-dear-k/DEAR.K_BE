package com.deark.be.event.service;

import static com.deark.be.event.exception.errorcode.EventErrorCode.EVENT_NOT_FOUND;
import static com.deark.be.event.exception.errorcode.EventErrorCode.NO_PERMISSION;
import static com.deark.be.user.exception.errorcode.UserErrorCode.USER_NOT_FOUND;

import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventDesign;
import com.deark.be.event.domain.EventStore;
import com.deark.be.event.domain.type.ThumbnailSource;
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
                .map(EventResponse::from)
                .toList();
    }

    public EventResponse getEventDetail(Long eventId, Long userId) {
        Event event = getValidatedEvent(eventId,userId);
        return EventResponse.from(event);
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
                .map(eventDesign -> DesignInEventResponse.of(
                        eventDesign,
                        eventDesign.getDesign()
                )).toList();
    }

    public void resolveAndUpdateThumbnail(Event event) {
        Optional<EventDesign> firstDesignOpt = eventDesignRepository
                .findTopByEventIdOrderByCreatedAtAsc(event.getId());

        Optional<EventStore> firstStoreOpt = eventStoreRepository
                .findTopByEventIdOrderByCreatedAtAsc(event.getId());

        String imageUrl = null;
        ThumbnailSource source = null;
        Long sourceId = null;

        if (firstDesignOpt.isPresent() && firstStoreOpt.isPresent()) {
            EventDesign design = firstDesignOpt.get();
            EventStore store = firstStoreOpt.get();

            if (design.getCreatedAt().isBefore(store.getCreatedAt())) {
                imageUrl = design.getDesign().getImageUrl();
                source = ThumbnailSource.DESIGN;
                sourceId = design.getDesign().getId();
            } else {
                imageUrl = store.getStore().getImageUrl();
                source = ThumbnailSource.STORE;
                sourceId = store.getStore().getId();
            }
        } else if (firstDesignOpt.isPresent()) {
            EventDesign design = firstDesignOpt.get();
            imageUrl = design.getDesign().getImageUrl();
            source = ThumbnailSource.DESIGN;
            sourceId = design.getDesign().getId();
        } else if (firstStoreOpt.isPresent()) {
            EventStore store = firstStoreOpt.get();
            imageUrl = store.getStore().getImageUrl();
            source = ThumbnailSource.STORE;
            sourceId = store.getStore().getId();
        }

        event.updateThumbnail(imageUrl, source, sourceId);
    }



    public List<EventWithCheckResponse> getMyEventsWithCheckForDesign(Long userId, Long designId) {
        User user=getValidatedUser(userId);

        List<Event> events = user.getEventList();

        return events.stream()
                .map(event -> {
                    boolean isChecked = eventDesignRepository.existsByEventIdAndDesignId(event.getId(), designId);
                    return EventWithCheckResponse.of(event, isChecked);
                })
                .collect(Collectors.toList());
    }

    public List<EventWithCheckResponse> getMyEventsWithCheckForStore(Long userId, Long storeId) {
        User user=getValidatedUser(userId);
        List<Event> events = user.getEventList();

        return events.stream()
                .map(event -> {
                    boolean isChecked = eventStoreRepository.existsByEventIdAndStoreId(event.getId(), storeId);
                    return EventWithCheckResponse.of(event, isChecked);
                })
                .collect(Collectors.toList());
    }

    public Event getValidatedEvent(Long eventId, Long userId) {
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
