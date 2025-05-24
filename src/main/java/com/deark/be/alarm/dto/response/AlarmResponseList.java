package com.deark.be.alarm.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record AlarmResponseList(
        Long alarmCount,
        List<AlarmResponse> responseList
) {
}