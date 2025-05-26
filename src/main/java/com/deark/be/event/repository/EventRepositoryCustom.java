package com.deark.be.event.repository;

import com.deark.be.event.domain.Event;
import java.util.Optional;

public interface EventRepositoryCustom {
    Optional<Event> findFirstUpcomingEvent(Long userId);
}
