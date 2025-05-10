package com.deark.be.order.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    REQUESTED("주문 요청"),
    ACCEPTED("주문 수락"),
    REJECTED("주문 거절"),
    PENDING("주문 대기"),
    ;

    private final String statusName;
}
