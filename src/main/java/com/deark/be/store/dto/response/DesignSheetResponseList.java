package com.deark.be.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record DesignSheetResponseList(
        @Schema(description = "가게에서 제공하는 시트 맛 리스트")
        List<DesignSheetResponse> sheetList
) {
    public static DesignSheetResponseList from(List<DesignSheetResponse> sheetList) {
        return DesignSheetResponseList.builder()
                .sheetList(sheetList)
                .build();
    }
}