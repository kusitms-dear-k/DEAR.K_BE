package com.deark.be.order.dto.response;

import com.deark.be.order.domain.type.Status;
import lombok.Builder;

@Builder
public record MyOrderCountResponse(
    Status status,
    Long count
) {
    public static MyOrderCountResponse of(Status status, Long count) {
        return MyOrderCountResponse.builder()
            .status(status)
            .count(count)
            .build();
    }
}
