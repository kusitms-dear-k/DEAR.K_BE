package com.deark.be.store.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record StoreDetailResponse(
        Long storeId,
        String storeName,
        String storeDescription,
        String storeImageUrl,
        String storeAddress,
        Boolean isSameDayOrder,
        Boolean is24hSelfService,
        Boolean isLunchBoxCake,
        List<BusinessHourResponse>businessHours,
        List<PickUpHourResponse>pickUpHours,
        String ownerName,
        Long likeCount,
        String businessNumber,
        List<String>sizeNameList
) {
}
