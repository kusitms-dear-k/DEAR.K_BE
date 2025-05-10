package com.deark.be.design.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DesignDetailResponse(
        String storeName,
        String designName,
        String designImageUrl,
        String description,
        Long price,
        Boolean isLiked,
        Long likeCount,
        List<String> sizeList,
        List<String> creamList,
        List<String> sheetList
) {
}