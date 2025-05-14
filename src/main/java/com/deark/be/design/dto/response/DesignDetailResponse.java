package com.deark.be.design.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record DesignDetailResponse(
        @Schema(description = "가게 이름", example = "디어케이 케이크")
        String storeName,
        @Schema(description = "디자인 이름", example = "루피 케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://cdn.deark.com/designs/3tier.png")
        String designImageUrl,
        @Schema(description = "디자인 설명", example = "루피를 테마로 한 귀여운 케이크입니다.")
        String description,
        @Schema(description = "디자인 최소 가격", example = "15000")
        Long price,
        @Schema(description = "찜하기 여부", example = "false")
        Boolean isLiked,
        @Schema(description = "좋아요 수", example = "157")
        Long likeCount,
        @Schema(description = "디자인 사이즈 리스트", example = "[\"도시락 케이크\", \"1호 케이크\", \"2호 케이크\"]")
        List<String> sizeList,
        @Schema(description = "크림 맛 리스트", example = "[\"생크림\", \"초코 크림\", \"딸기 크림\"]")
        List<String> creamList,
        @Schema(description = "시트 리스트", example = "[\"바닐라\", \"초코\", \"블루베리\"]")
        List<String> sheetList
) {
}