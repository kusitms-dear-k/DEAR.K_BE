package com.deark.be.order.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MyOrderPendingResponseList(
        List<MyOrderPendingResponse> responseList
) {
    public static MyOrderPendingResponseList from(List<MyOrderPendingResponse> responseList) {
        return MyOrderPendingResponseList.builder()
                .responseList(responseList)
                .build();
    }
}