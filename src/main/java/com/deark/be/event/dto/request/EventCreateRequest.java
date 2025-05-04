package com.deark.be.event.dto.request;

import com.deark.be.event.domain.Event;
import com.deark.be.user.domain.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EventCreateRequest(
        @Schema(description = "이벤트 제목", example = "친구 생일 파티")
        @NotBlank(message = "이벤트 이름은 필수입니다.")
        String title,

        @Schema(description = "이벤트 날짜", example = "2025-12-25")
        @NotNull(message = "이벤트 날짜는 필수입니다.")
        LocalDate eventDate,

        @Schema(description = "이벤트 장소", example = "서울시 강남구")
        @NotBlank(message = "이벤트 장소는 필수입니다.")
        String address
) {
    public Event toEntity(User user) {
        return Event.builder()
                .user(user)
                .title(title)
                .address(address)
                .eventDate(eventDate)
                .build();
    }
}
