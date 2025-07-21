package com.deark.be.order.dto.response;

import com.deark.be.order.domain.OrderRequestFormQa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record QAResponse(
        String title,
        String answer,
        Boolean isRequired,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String requestDetailImageUrl
) {
    public static QAResponse from(OrderRequestFormQa orderRequestFormQa) {
        return QAResponse.builder()
                .title(orderRequestFormQa.getQuestion())
                .answer(orderRequestFormQa.getAnswer())
                .isRequired(orderRequestFormQa.getIsRequired())
                .requestDetailImageUrl("추가 요청사항".equals(orderRequestFormQa.getQuestion()) ? orderRequestFormQa.getOrderRequestForm().getCakeDesign().getImageUrl() : null)
                .build();
    }
}

