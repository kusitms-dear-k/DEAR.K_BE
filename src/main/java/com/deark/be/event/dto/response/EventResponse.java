package com.deark.be.event.dto.response;

import com.deark.be.event.domain.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record EventResponse(
        @Schema(description = "이벤트 ID", example = "1")
        Long eventId,
        @Schema(description = "이벤트 제목", example = "생일 파티")
        String title,
        @Schema(description = "이벤트 주소", example = "서울특별시 강남구 테헤란로 123")
        String address,
        @Schema(description = "이벤트 날짜", example = "2025-06-01")
        LocalDate eventDate,
        @Schema(description = "대표 썸네일 이미지 URL", example = "https://cdn.deark.com/thumbnails/event_1.png")
        String thumbnailUrl
) {
    public static EventResponse from(Event event) {
        return EventResponse.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .address(event.getAddress())
                .eventDate(event.getEventDate())
                .thumbnailUrl(event.getThumbnailUrl())
                .build();
    }
}
