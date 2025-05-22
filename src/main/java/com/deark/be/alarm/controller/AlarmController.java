package com.deark.be.alarm.controller;

import com.deark.be.alarm.dto.request.DeleteAlarmRequest;
import com.deark.be.alarm.dto.response.AlarmResponseList;
import com.deark.be.alarm.service.AlarmService;
import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.order.domain.type.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.deark.be.global.dto.ResponseTemplate.EMPTY_RESPONSE;

@Tag(name = "Alarm", description = "알림 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @Operation(summary = "알림 전체 조회", description = "전체 알림을 조회 : Status를 null로 설정 <br>" +
            "수락된 알림만 조회 : Status를 ACCEPTED로 설정 <br>")
    @GetMapping()
    public ResponseEntity<ResponseTemplate<AlarmResponseList>> getAlarmList(
            @AuthenticationPrincipal Long userId,
            @RequestParam(required = false) OrderStatus orderStatus) {

        AlarmResponseList responseList = alarmService.getAlarmList(userId, orderStatus);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "여러 개의 알림 삭제", description = "여러 개의 알림을 삭제합니다.")
    @DeleteMapping()
    public ResponseEntity<ResponseTemplate<Object>> deleteAlarm(
            @RequestBody DeleteAlarmRequest request) {

        alarmService.markAlarmAsDeleted(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EMPTY_RESPONSE);
    }
}