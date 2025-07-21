package com.deark.be.order.dto.response;

import com.deark.be.order.domain.OrderRequestFormQa;
import lombok.Builder;

@Builder
public record QAStatusResponse(
        String title,
        String answer
) {
    public static QAStatusResponse from(OrderRequestFormQa orderRequestFormQa) {
        return QAStatusResponse.builder()
                .title(orderRequestFormQa.getQuestion())
                .answer(orderRequestFormQa.getAnswer())
                .build();
    }
}
