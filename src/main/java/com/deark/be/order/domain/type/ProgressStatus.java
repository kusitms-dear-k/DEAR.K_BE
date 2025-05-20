package com.deark.be.order.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProgressStatus {
    RESERVED("예약 완료"),
    BAKING("베이킹 완료"),
    PICKUP_DONE("픽업 완료"),
    ;

    private final String description;
}
