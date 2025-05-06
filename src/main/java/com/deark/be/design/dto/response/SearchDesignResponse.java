package com.deark.be.design.dto.response;

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
}