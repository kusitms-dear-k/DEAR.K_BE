package com.deark.be.order.dto.response;

import com.deark.be.order.domain.OrderRequestForm;
import lombok.Builder;

@Builder
public record MyOrderRejectedResponse(
        String reason
) {
    public static MyOrderRejectedResponse from(OrderRequestForm orderRequestForm) {
        return MyOrderRejectedResponse.builder()
                .reason(orderRequestForm.getMakerResponse())
                .build();
    }
}
