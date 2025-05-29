package com.deark.be.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record SearchStoreResponse(
        @Schema(description = "가게 ID", example = "1")
        Long storeId,
        @Schema(description = "가게 이름", example = "디어케이 케이크")
        String storeName,
        @Schema(description = "가게 이미지 URL", example = "https://cdn.deark.com/stores/store.png")
        String storeImageUrl,
        @Schema(description = "가게 주소", example = "서울특별시 강남구 역삼동 123-45")
        String address,
        @Schema(description = "당일 주문 가능 여부", example = "true")
        Boolean isSameDayOrder,
        @Schema(description = "무인 가게 운영 여부", example = "false")
        Boolean isSelfService,
        @Schema(description = "도시락 케이크 가능 여부", example = "true")
        Boolean isLunchBoxCake,
        @Schema(description = "찜하기 여부", example = "false")
        Boolean isLiked,
        @Schema(description = "좋아요 수", example = "157")
        Long likeCount,
        @Schema(description = "디자인 이미지 URL 리스트", example = "[\"https://cdn.deark.com/designs/3tier.png\", \"https://cdn.deark.com/designs/2tier.png\"]")
        List<String> designImageUrlList
) {
}