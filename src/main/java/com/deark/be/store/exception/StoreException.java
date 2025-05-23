package com.deark.be.store.exception;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreException extends RuntimeException {
    private final ErrorCode errorCode;
}
