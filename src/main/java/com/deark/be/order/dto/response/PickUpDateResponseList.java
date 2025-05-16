package com.deark.be.order.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PickUpDateResponseList(
        List<PickUpDateResponse> responseList
) {
    public static PickUpDateResponseList from(List<PickUpDateResponse> responseList) {
        return PickUpDateResponseList.builder()
                .responseList(responseList)
                .build();
    }
}
