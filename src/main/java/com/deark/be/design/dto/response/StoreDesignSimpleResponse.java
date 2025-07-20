package com.deark.be.design.dto.response;

import com.deark.be.design.domain.CakeDesign;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StoreDesignSimpleResponse(
        @Schema(description = "디자인 ID", example = "1")
        Long designId,
        @Schema(description = "디자인 이름", example = "레인보우케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://cdn.deark.com/designs/rainbow.png")
        String designImageUrl,
        @Schema(description = "디자인 가격", example = "19000")
        Long price
) {
    public static StoreDesignSimpleResponse from(CakeDesign cakeDesign) {
        return StoreDesignSimpleResponse.builder()
                .designId(cakeDesign.getId())
                .designName(cakeDesign.getName())
                .designImageUrl(cakeDesign.getImageUrl())
                .price(cakeDesign.getPrice())
                .build();
    }
}
