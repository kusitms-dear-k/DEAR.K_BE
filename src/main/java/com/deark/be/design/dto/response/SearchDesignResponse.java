package com.deark.be.design.dto.response;

import com.deark.be.design.domain.Design;
import lombok.Builder;

@Builder
public record SearchDesignResponse(
        Long id,
        String designName,
        String storeName,
        Long price,
        String address,
        Boolean isSameDayOrder
) {
    public static SearchDesignResponse from(Design design) {
        return SearchDesignResponse.builder()
                .id(design.getId())
                .designName(design.getName())
                .storeName(design.getStore().getName())
                .price(design.getPrice())
                .address(design.getStore().getAddress())
                .isSameDayOrder(design.getStore().getIsSameDayOrder())
                .build();
    }
}