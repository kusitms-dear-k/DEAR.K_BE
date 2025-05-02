package com.deark.be.design.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchDesignResponseList(
        Long page,
        Boolean hasNext,
        List<SearchDesignResponse> designList
) {
    public static SearchDesignResponseList of(Long page, Boolean hasNext, List<SearchDesignResponse> designList) {
        return SearchDesignResponseList.builder()
                .page(page)
                .hasNext(hasNext)
                .designList(designList)
                .build();
    }
}