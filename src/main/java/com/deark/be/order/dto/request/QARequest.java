package com.deark.be.order.dto.request;

import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.OrderRequestFormQa;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주문서 질문과 답변에 대한 정보")
public record QARequest(
        @Schema(description = "질문", example = "이름")
        String title,

        @Schema(description = "답변", example = "김혜연")
        String answer,

        Boolean isRequired
) {
    public OrderRequestFormQa toEntity(OrderRequestForm orderRequestForm) {
        return OrderRequestFormQa.builder()
                .orderRequestForm(orderRequestForm)
                .question(title)
                .answer(answer)
                .build();
    }
}