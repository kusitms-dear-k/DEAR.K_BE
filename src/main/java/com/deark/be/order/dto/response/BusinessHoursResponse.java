package com.deark.be.order.dto.response;

import com.deark.be.store.domain.BusinessHours;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record BusinessHoursResponse(
        @Schema(description = "시작 시간", example = "10:00")
        String openTime,
        @Schema(description = "종료 시간", example = "20:00")
        String closeTime
) {
    public static BusinessHoursResponse from(BusinessHours businessHours) {
        return BusinessHoursResponse.builder()
                .openTime(businessHours.getOpenTime().toString())
                .closeTime(businessHours.getCloseTime().toString())
                .build();
    }
}