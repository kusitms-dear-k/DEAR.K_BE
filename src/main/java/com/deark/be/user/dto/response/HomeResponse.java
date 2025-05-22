package com.deark.be.user.dto.response;

import lombok.Builder;

@Builder
public record HomeResponse(
        String userName,
        Boolean isAlarm
) {
    public static HomeResponse of(String userName, Boolean isAlarm) {
        return HomeResponse.builder()
                .userName(userName)
                .isAlarm(isAlarm)
                .build();
    }
}
