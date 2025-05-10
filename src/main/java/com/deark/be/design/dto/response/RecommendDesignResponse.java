package com.deark.be.design.dto.response;

import com.deark.be.design.domain.Design;
import lombok.Builder;

@Builder
public record RecommendDesignResponse(
        Long designId,
        String designName,
        String designImageUrl,
        String storeName,
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