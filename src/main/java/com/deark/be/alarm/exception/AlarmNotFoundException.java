package com.deark.be.alarm.exception;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlarmNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
}
