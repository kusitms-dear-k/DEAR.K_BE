package com.deark.be.order.dto.response;

import com.deark.be.order.domain.type.OrderStatus;
import lombok.Builder;

@Builder
public record MyOrderCountResponse(
    OrderStatus orderStatus,
    Long count
) {
    public static MyOrderCountResponse of(OrderStatus orderStatus, Long count) {
        return MyOrderCountResponse.builder()
            .orderStatus(orderStatus)
            .count(count)
            .build();
    }
}
