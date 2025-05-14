package com.deark.be.design.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StoreDesignResponse(
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
        @Schema(description = "찜하기 여부", example = "false")
        Boolean isLiked
) {
}