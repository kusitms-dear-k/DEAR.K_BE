package com.deark.be.order.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record OrderQuestionResponseList(
        List<OrderQuestionResponse> responseList
) {
    public static OrderQuestionResponseList from(List<OrderQuestionResponse> responseList) {
        return OrderQuestionResponseList.builder()
                .responseList(responseList)
                .build();
    }
}
