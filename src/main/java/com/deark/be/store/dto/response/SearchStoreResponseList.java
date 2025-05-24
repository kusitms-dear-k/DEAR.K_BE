package com.deark.be.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record SearchStoreResponseList(
        @Schema(description = "가게 총 갯수", example = "247")
        Long totalCount,
        @Schema(description = "현재 페이지", example = "0")
        Long page,
        @Schema(description = "다음 페이지 존재 여부", example = "true")
        Boolean hasNext,
        @Schema(description = "가게 리스트")
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