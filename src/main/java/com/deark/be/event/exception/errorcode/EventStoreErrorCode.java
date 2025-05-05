package com.deark.be.event.exception.errorcode;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EventStoreErrorCode implements ErrorCode {
    EVENT_STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "EVENT_STORE_400", "이벤트에서 해당 스토어를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
