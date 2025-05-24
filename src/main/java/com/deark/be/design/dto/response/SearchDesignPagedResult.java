package com.deark.be.design.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchDesignPagedResult(
        Long totalCount,
        List<SearchDesignResponse> designList
) {
    public static SearchDesignPagedResult of(Long totalCount, List<SearchDesignResponse> designList) {
        return SearchDesignPagedResult.builder()
                .totalCount(totalCount)
                .designList(designList)
                .build();
    }
}
