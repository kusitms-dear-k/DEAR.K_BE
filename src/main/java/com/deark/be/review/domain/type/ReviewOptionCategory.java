package com.deark.be.review.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewOptionCategory {
    DESIGN_OPTION("디자인 옵션"),
    SIZE_OPTION("사이즈 옵션"),
    SHEET_OPTION("시트 옵션"),
    CREAM_OPTION("크림 옵션"),
    DESIGN_REVIEW("디자인 리뷰"),
    TASTE_REVIEW("맛.신선도 리뷰"),
    COMMUNICATION_REVIEW("소통 리뷰"),
    IMAGE("사진"),
    CONTENT("상세 후기")
    ;

    private final String description;
}
