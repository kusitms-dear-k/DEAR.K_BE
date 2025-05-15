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
        Boolean isLiked,
        List<BusinessHourResponse> businessHours,
        List<PickUpHourResponse> pickUpHours,
        String ownerName,
        Long likeCount,
        String businessNumber,
        List<String> sizeNameList
) {
    public static StoreDetailResponse of(
            Store store,
            boolean is24hSelfService,
            boolean isLunchBoxCake,
            boolean isLiked,
            List<BusinessHourResponse> businessHours,
            List<PickUpHourResponse> pickupHours,
            Long likeCount,
            List<String> sizeNameList
    ) {
        return StoreDetailResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeDescription(store.getDescription())
                .storeImageUrl(store.getImageUrl())
                .storeAddress(store.getAddress())
                .isSameDayOrder(store.getIsSameDayOrder())
                .is24hSelfService(is24hSelfService)
                .isLunchBoxCake(isLunchBoxCake)
                .isLiked(isLiked)
                .businessHours(businessHours)
                .pickUpHours(pickupHours)
                .ownerName(store.getOwnerName())
                .likeCount(likeCount)
                .businessNumber(store.getBusinessNumber())
                .sizeNameList(sizeNameList)
                .build();
    }
}
