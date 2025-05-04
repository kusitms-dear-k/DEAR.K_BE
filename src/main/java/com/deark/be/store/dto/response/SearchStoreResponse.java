package com.deark.be.store.dto.response;

import com.deark.be.store.domain.Store;
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
    public static SearchStoreResponse of(Store store, List<String> designImageUrlList, Boolean isLiked) {
        return SearchStoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeImageUrl(store.getImageUrl())
                .address(store.getAddress())
                .isSameDayOrder(store.getIsSameDayOrder())
                .isUnmanned(store.getIsUnmanned())
                .isLunchBoxCake(true)
                .isLiked(isLiked)
                .designImageUrlList(designImageUrlList)
                .build();
    }
}