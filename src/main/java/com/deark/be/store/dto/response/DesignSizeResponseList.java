package com.deark.be.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record DesignSizeResponseList(
        @Schema(description = "디자인 사이즈 리스트")
        List<DesignSizeResponse> sizeList
) {
    public static DesignSizeResponseList from(List<DesignSizeResponse> sizeList) {
        return DesignSizeResponseList.builder()
                .sizeList(sizeList)
                .build();
    }
}