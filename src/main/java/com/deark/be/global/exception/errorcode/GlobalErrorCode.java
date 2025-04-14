package com.deark.be.global.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Dto @Valid 관련 error 해결을 위한 enum class
 */
@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON404", "유효하지 않은 파라미터입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}