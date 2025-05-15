package com.deark.be.order.exception.errorcode;

import com.deark.be.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {

    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER400", "주문을 찾을 수 없습니다."),
    MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER401", "쪽지를 찾을 수 없습니다."),
    ORDER_NOT_ACCEPTED(HttpStatus.BAD_REQUEST, "ORDER402", "수락된 주문이 아닙니다."),
    ORDER_NOT_REJECTED(HttpStatus.BAD_REQUEST, "ORDER403", "거절된 주문이 아닙니다."),

    INVALID_STORE_DESIGN_CONFLICT(HttpStatus.BAD_REQUEST, "ORDER410", "디자인 타입이 STORE일 경우, design만 존재해야 하며 designUrl은 없어야 합니다."),
    INVALID_CUSTOM_DESIGN_CONFLICT(HttpStatus.BAD_REQUEST, "ORDER411", "디자인 타입이 CUSTOM일 경우, designUrl만 존재해야 하며 design은 없어야 합니다."),
    INVALID_EVENT_REQUEST_DETAIL_CONFLICT(HttpStatus.BAD_REQUEST, "ORDER412", "추가 요청사항 타입이 EVENT일 경우, requestDetailDesign만 존재해야 합니다."),
    INVALID_CUSTOM_REQUEST_DETAIL_CONFLICT(HttpStatus.BAD_REQUEST, "ORDER413", "추가 요청사항 타입이 CUSTOM일 경우, requestDetailImageUrl만 존재해야 합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
