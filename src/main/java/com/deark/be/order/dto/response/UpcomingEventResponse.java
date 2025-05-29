package com.deark.be.order.dto.response;

import com.deark.be.event.domain.Event;
import lombok.Builder;

@Builder
public record UpcomingEventResponse(
        String eventTitle,
        Long dDay
) {
    public static UpcomingEventResponse of(Event event, Long dDay) {
        return UpcomingEventResponse.builder()
                .eventTitle(event.getTitle())
                .dDay(dDay)
                .build();
    }
}
