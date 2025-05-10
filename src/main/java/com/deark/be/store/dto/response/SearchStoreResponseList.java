package com.deark.be.store.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchStoreResponseList(
        Long totalCount,
        Long page,
        Boolean hasNext,
        List<SearchStoreResponse> storeList
) {
    public static SearchStoreResponseList of(Long totalCount, Long page, Boolean hasNext, List<SearchStoreResponse> storeList) {
        return SearchStoreResponseList.builder()
                .totalCount(totalCount)
                .page(page)
                .hasNext(hasNext)
                .storeList(storeList)
                .build();
    }
}