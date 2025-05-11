package com.deark.be.order.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    REQUESTED("주문 요청"),
    ACCEPTED("수락"),
    REJECTED("반려"),
    PENDING("응답 대기"),
    ;

    private final String statusName;
}
