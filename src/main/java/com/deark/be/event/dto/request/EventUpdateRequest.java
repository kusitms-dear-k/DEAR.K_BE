package com.deark.be.event.dto.request;

import com.deark.be.event.domain.Event;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EventUpdateRequest(
        @Schema(description = "수정할 이벤트 제목", example = "송년회 모임")
        @NotBlank(message = "이벤트 이름은 필수입니다.")
        String title,

        @Schema(description = "수정할 이벤트 날짜", example = "2025-12-31")
        @NotNull(message = "이벤트 날짜는 필수입니다.")
        LocalDate eventDate,

        @Schema(description = "수정할 이벤트 장소", example = "부산 해운대")
        @NotBlank(message = "이벤트 장소는 필수입니다.")
        String address
) {
    public void updateEvent(Event event) {
        event.update(this.title, this.address,this.eventDate);
    }
}
