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

    // 공통 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON404", "유효하지 않은 파라미터입니다."),

    // 인증 관련 에러
    JWT_ROLE_ERROR(HttpStatus.FORBIDDEN, "AUTH400", "가진 권한으로는 실행할 수 없는 기능입니다."),
    JWT_AUTH_ERROR(HttpStatus.UNAUTHORIZED, "AUTH401", "로그인 후 다시 접근해주시기 바랍니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH402", "유효하지 않은 토큰입니다."),

    // FILE 관련 에러
    FILE_CONVERT_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "FILE500", "파일 변환 중 에러가 발생했습니다."),
    FILE_NOT_UPLOADED(HttpStatus.BAD_REQUEST, "FILE502", "파일이 업로드되지 않았습니다."),
    FILE_NOT_IMAGE(HttpStatus.BAD_REQUEST, "FILE400", "이미지 파일이 아닙니다."),
    FILE_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "FILE503", "파일 삭제 중 에러가 발생했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}