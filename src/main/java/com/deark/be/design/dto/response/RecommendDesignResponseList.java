package com.deark.be.design.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RecommendDesignResponseList(
        List<RecommendDesignResponse> designList
) {
    public static RecommendDesignResponseList from(List<RecommendDesignResponse> designList) {
        return RecommendDesignResponseList.builder()
                .designList(designList)
                .build();
    }
}