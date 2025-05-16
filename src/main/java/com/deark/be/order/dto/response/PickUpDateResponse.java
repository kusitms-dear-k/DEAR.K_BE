package com.deark.be.order.dto.response;

import com.deark.be.store.domain.type.BusinessDay;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PickUpDateResponse(
        @Schema(description = "픽업 가능 요일", example = "월요일")
        String dayName
) {
    public static PickUpDateResponse from(BusinessDay businessDay) {
        return PickUpDateResponse.builder()
                .dayName(businessDay.getDayName())
                .build();
    }
}