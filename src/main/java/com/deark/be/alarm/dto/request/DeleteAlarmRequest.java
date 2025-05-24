package com.deark.be.alarm.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record DeleteAlarmRequest(
        @Schema(description = "알람 ID 리스트", example = "[1, 2, 3]")
        List<Long> alarmIdList
) {
}
