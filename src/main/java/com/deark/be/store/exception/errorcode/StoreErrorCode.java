package com.deark.be.store.exception.errorcode;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements ErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE400", "가게를 찾을 수 없습니다."),
    BUSINESS_HOURS_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE401", "가게의 운영시간이 아닙니다"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
