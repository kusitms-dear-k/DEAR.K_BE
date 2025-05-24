package com.deark.be.order.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record OrderManagementResponseList(
        List<OrderManagementResponse> responseList,
        int totalNum
) {
    public static OrderManagementResponseList from(List<OrderManagementResponse> responseList) {
        return OrderManagementResponseList.builder()
                .responseList(responseList)
                .totalNum(responseList.size())
                .build();
    }
}
