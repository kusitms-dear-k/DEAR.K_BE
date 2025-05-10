package com.deark.be.event.exception.errorcode;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EventErrorCode implements ErrorCode {

    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "EVENT400", "내 이벤트를 찾을 수 없습니다."),
    NO_PERMISSION(HttpStatus.FORBIDDEN, "EVENT403", "이벤트에 대한 권한이 없습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
