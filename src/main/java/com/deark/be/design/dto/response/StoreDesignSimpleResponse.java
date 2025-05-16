package com.deark.be.design.dto.response;

import com.deark.be.design.domain.Design;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StoreDesignSimpleResponse(
        @Schema(description = "디자인 ID", example = "1")
        Long designId,
        @Schema(description = "디자인 이름", example = "레인보우케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://cdn.deark.com/designs/rainbow.png")
        String imageUrl,
        @Schema(description = "디자인 가격", example = "19000")
        Long price
) {
    public static StoreDesignResponse from(Design design) {
        return StoreDesignResponse.builder()
                .designId(design.getId())
                .designName(design.getName())
                .designImageUrl(design.getImageUrl())
                .price(design.getPrice())
                .build();
    }
}
