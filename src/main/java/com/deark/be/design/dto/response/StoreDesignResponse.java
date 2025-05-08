package com.deark.be.design.dto.response;

import lombok.Builder;

@Builder
public record StoreDesignResponse(
        Long designId,
        String designName,
        String designImageUrl,
        String storeName,
        Long price,
        Boolean isLiked
) {
}