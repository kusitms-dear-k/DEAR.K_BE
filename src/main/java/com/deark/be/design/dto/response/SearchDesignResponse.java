package com.deark.be.design.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SearchDesignResponse(
        @Schema(description = "디자인 ID", example = "1")
        Long designId,
        @Schema(description = "디자인 이름", example = "루피 케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://cdn.deark.com/designs/3tier.png")
        String designImageUrl,
        @Schema(description = "디자인을 업로드한 스토어 이름", example = "디어케이 케이크")
        String storeName,
        @Schema(description = "디자인 최소 가격", example = "15000")
        Long price,
        @Schema(description = "가게 주소", example = "서울특별시 강남구 역삼동 123-45")
        String address,
        @Schema(description = "당일 주문 가능 여부", example = "true")
        Boolean isSameDayOrder,
        @Schema(description = "찜하기 여부", example = "false")
        Boolean isLiked,
        @Schema(description = "좋아요 수", example = "157")
        Long likeCount
) {
}