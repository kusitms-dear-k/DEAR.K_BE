package com.deark.be.order.dto.response;

import com.deark.be.order.domain.Message;
import lombok.Builder;

@Builder
public record MyOrderRejectedResponse(
        String reason
) {
    public static MyOrderRejectedResponse from(Message message) {
        return MyOrderRejectedResponse.builder()
                .reason(message.getMakerResponse())
                .build();
    }
}
