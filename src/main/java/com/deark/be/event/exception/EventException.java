package com.deark.be.event.exception;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EventException extends RuntimeException {
    private final ErrorCode errorCode;
}
