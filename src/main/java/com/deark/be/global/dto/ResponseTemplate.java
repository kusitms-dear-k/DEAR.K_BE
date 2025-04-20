package com.deark.be.global.dto;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Builder;

import java.util.Collections;

@Builder
public record ResponseTemplate<T>(
        Boolean isSuccess,
        String code,
        String message,
        T results
) {
    public static final ResponseTemplate<Object> EMPTY_RESPONSE = ResponseTemplate.builder()
            .isSuccess(true)
            .code("REQUEST_OK")
            .message("요청이 승인되었습니다.")
            .results(Collections.EMPTY_MAP)
            .build();

    public static <T> ResponseTemplate<Object> from(T dto) {
        return ResponseTemplate.builder()
                .isSuccess(true)
                .code("REQUEST_OK")
                .message("요청이 승인되었습니다.")
                .results(dto)
                .build();
    }

    public static <T> ResponseTemplate<Object> from(ErrorCode errorCode) {
        return ResponseTemplate.builder()
                .isSuccess(false)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .results(Collections.EMPTY_MAP)
                .build();
    }
}
