package com.deark.be.store.dto.response;

import com.deark.be.store.domain.Store;
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
    public static StoreDetailResponse from(
            Store store,
            boolean is24hSelfService,
            boolean isLunchBoxCake,
            List<BusinessHourResponse> businessHours,
            List<PickUpHourResponse> pickupHours,
            Long likeCount,
            List<String> sizeNameList
    ) {
        return new StoreDetailResponse(
                store.getId(),
                store.getName(),
                store.getDescription(),
                store.getImageUrl(),
                store.getAddress(),
                store.getIsSameDayOrder(),
                is24hSelfService,
                isLunchBoxCake,
                businessHours,
                pickupHours,
                store.getOwnerName(),
                likeCount,
                store.getBusinessNumber(),
                sizeNameList
        );
    }
}
