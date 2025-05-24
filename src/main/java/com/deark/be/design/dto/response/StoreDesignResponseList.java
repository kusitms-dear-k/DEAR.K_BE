package com.deark.be.design.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record StoreDesignResponseList(
        @Schema(description = "현재 페이지 수", example = "0")
        Long page,
        @Schema(description = "다음 페이지 존재 여부", example = "true")
        Boolean hasNext,
        @Schema(description = "디자인 리스트")
        List<StoreDesignResponse> designList
) {
    public static StoreDesignResponseList of(Long page, Boolean hasNext, List<StoreDesignResponse> designList) {
        return StoreDesignResponseList.builder()
                .page(page)
                .hasNext(hasNext)
                .designList(designList)
                .build();
    }
}