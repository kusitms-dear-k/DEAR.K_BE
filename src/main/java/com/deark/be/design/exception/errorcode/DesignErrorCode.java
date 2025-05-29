package com.deark.be.design.exception.errorcode;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DesignErrorCode implements ErrorCode {

    DESIGN_NOT_FOUND(HttpStatus.NOT_FOUND, "DESIGN400", "케이크 디자인을 찾을 수 없습니다."),
    SIZE_NOT_FOUND(HttpStatus.NOT_FOUND, "DESIGN401", "케이크 사이즈를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
