package com.deark.be.design.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchDesignResponseList(
        Long totalCount,
        Long page,
        Boolean hasNext,
        List<SearchDesignResponse> designList
) {
    public static SearchDesignResponseList of(Long totalCount, Long page, Boolean hasNext, List<SearchDesignResponse> designList) {
        return SearchDesignResponseList.builder()
                .totalCount(totalCount)
                .page(page)
                .hasNext(hasNext)
                .designList(designList)
                .build();
    }
}