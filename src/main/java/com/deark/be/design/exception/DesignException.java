package com.deark.be.design.exception;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DesignException extends RuntimeException {
    private final ErrorCode errorCode;
}
