package com.deark.be.design.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record StoreDesignResponseList(
        Long page,
        Boolean hasNext,
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