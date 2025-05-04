package com.deark.be.design.dto.response;

import com.deark.be.design.domain.Design;
import lombok.Builder;

@Builder
public record SearchDesignResponse(
        Long designId,
        String designName,
        String designImageUrl,
        String storeName,
        Long price,
        String address,
        Boolean isSameDayOrder,
        Boolean isLiked
) {
    public static SearchDesignResponse from(Design design) {
        return SearchDesignResponse.builder()
                .designId(design.getId())
                .designImageUrl(design.getImageUrl())
                .designName(design.getName())
                .storeName(design.getStore().getName())
                .price(design.getPrice())
                .address(design.getStore().getAddress())
                .isSameDayOrder(design.getStore().getIsSameDayOrder())
                .isLiked(false)
                .build();
    }
}