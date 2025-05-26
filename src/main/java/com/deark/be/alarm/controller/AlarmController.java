package com.deark.be.alarm.controller;

import com.deark.be.alarm.dto.request.DeleteAlarmRequest;
import com.deark.be.alarm.dto.request.ReadAlarmRequest;
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

    @Operation(summary = "알림 전체 조회", description = "전체 알림을 조회 : orderStatus를 null로 설정 <br>" +
            "수락된 알림만 조회 : orderStatus를 ACCEPTED로 설정 <br><br>" +
            "responseStatus -> CANCELED (피커 주문 취소) / PAID 또는 UNRESPONSIVE (피커 주문 취소 X) <br><br>" +
            "alarmDateTime 은 2025-05-01 12:00:00 형식으로 제공됩니다.")
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

    @Operation(summary = "알림 읽음 처리", description = "알림을 읽음 처리합니다.")
    @PutMapping("/read")
    public ResponseEntity<ResponseTemplate<Object>> readAlarm(
            @RequestBody ReadAlarmRequest request) {

        alarmService.markAlarmAsRead(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EMPTY_RESPONSE);
    }
}