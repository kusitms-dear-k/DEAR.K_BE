package com.deark.be.order.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MyOrderStatusResponseList(
        List<MyOrderStatusResponse> responseList
) {
    public static MyOrderStatusResponseList from(List<MyOrderStatusResponse> responseList) {
        return MyOrderStatusResponseList.builder()
                .responseList(responseList)
                .build();
    }
}