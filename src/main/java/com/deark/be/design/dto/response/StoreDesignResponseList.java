package com.deark.be.design.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record StoreDesignResponseList(
        List<StoreDesignResponse> designList
) {
    public static StoreDesignResponseList from(List<StoreDesignResponse> designList) {
        return StoreDesignResponseList.builder()
                .designList(designList)
                .build();
    }
}