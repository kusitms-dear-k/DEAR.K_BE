package com.deark.be.store.dto.response;


public record BusinessHourResponse(
        String dayName,
        String openTime,
        String closeTime
) {
}
