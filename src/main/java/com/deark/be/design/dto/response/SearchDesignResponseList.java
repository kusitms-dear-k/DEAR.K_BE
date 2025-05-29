package com.deark.be.design.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record SearchDesignResponseList(
        @Schema(description = "디자인 총 갯수", example = "133")
        Long totalCount,
        @Schema(description = "현재 페이지", example = "0")
        Long page,
        @Schema(description = "다음 페이지 존재 여부", example = "true")
        Boolean hasNext,
        @Schema(description = "디자인 리스트")
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