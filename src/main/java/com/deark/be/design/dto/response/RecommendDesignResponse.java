package com.deark.be.design.dto.response;

import com.deark.be.design.domain.Design;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RecommendDesignResponse(
        @Schema(description = "디자인 ID", example = "1")
        Long designId,
        @Schema(description = "디자인 이름", example = "루피 케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://cdn.deark.com/designs/3tier.png")
        String designImageUrl,
        @Schema(description = "디자인을 업로드한 스토어 이름", example = "디어케이 케이크")
        String storeName,
        @Schema(description = "찜하기 여부", example = "false")
        Boolean isLiked
) {
    public static RecommendDesignResponse of(Design design, Boolean isLiked) {
        return RecommendDesignResponse.builder()
                .designId(design.getId())
                .designImageUrl(design.getImageUrl())
                .designName(design.getName())
                .storeName(design.getStore().getName())
                .isLiked(isLiked)
                .build();
    }
}