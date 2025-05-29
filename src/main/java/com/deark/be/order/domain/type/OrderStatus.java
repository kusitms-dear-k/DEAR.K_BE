package com.deark.be.order.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("응답 대기"),
    ACCEPTED("수락"),
    REJECTED("반려"),
    ;

    private final String statusName;
}
