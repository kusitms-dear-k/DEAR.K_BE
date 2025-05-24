package com.deark.be.order.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MyOrderCountResponseList(
    List<MyOrderCountResponse> responseList
) {
    public static MyOrderCountResponseList from(List<MyOrderCountResponse> responseList) {
        return MyOrderCountResponseList.builder()
                .responseList(responseList)
                .build();
    }
}
