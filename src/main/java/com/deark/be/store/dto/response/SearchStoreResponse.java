package com.deark.be.store.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchStoreResponse(
        Long storeId,
        String storeName,
        String storeImageUrl,
        String address,
        Boolean isSameDayOrder,
        Boolean isUnmanned,
        Boolean isLunchBoxCake,
        Boolean isLiked,
        List<String> designImageUrlList
) {
}