package com.deark.be.event.dto.response;

import com.deark.be.event.domain.EventStore;
import com.deark.be.store.domain.Store;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StoreInEventResponse(
        @Schema(description = "스토어 ID", example = "10")
        Long storeId,
        @Schema(description = "스토어 이름", example = "달콤한 케이크점")
        String storeName,
        @Schema(description = "스토어 대표 이미지 URL", example = "https://cdn.deark.com/stores/store10.png")
        String storeImageUrl,
        @Schema(description = "스토어 주소", example = "서울특별시 마포구 양화로 45")
        String storeAddress,
        @Schema(description = "해당 스토어에 대한 메모", example = "추천받은 가게")
        String memo
) {
    public static StoreInEventResponse from(EventStore eventStore) {
        Store store=eventStore.getStore();
        return StoreInEventResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeImageUrl(store.getImageUrl())
                .storeAddress(store.getAddress())
                .memo(eventStore.getMemo())
                .build();
    }
}
