package com.deark.be.store.dto.response;

import com.deark.be.design.dto.response.SearchDesignResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record SearchStorePagedResult(
        Long totalCount,
        Boolean hasNext,
        List<SearchStoreResponse> storeList
) {
    public static SearchStorePagedResult of(Long totalCount, Boolean hasNext, List<SearchStoreResponse> storeList) {
        return SearchStorePagedResult.builder()
                .totalCount(totalCount)
                .hasNext(hasNext)
                .storeList(storeList)
                .build();
    }
}
