package com.deark.be.alarm.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AlarmErrorCode {

    ALARM_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM400", "알림을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
