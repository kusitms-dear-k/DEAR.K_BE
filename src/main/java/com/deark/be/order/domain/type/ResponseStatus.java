package com.deark.be.order.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
    CANCELED("주문 취소"),
    PAID("입금 완료"),
    UNRESPONSIVE("미응답"),
    ;

    private final String description;
}
