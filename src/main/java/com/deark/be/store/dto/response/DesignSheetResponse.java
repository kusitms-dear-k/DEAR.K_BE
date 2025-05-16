package com.deark.be.store.dto.response;

import com.deark.be.design.domain.Sheet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record DesignSheetResponse(
        @Schema(description = "시트 맛", example = "바닐라")
        String sheetName
) {
    public static DesignSheetResponse from(Sheet sheet) {
        return DesignSheetResponse.builder()
                .sheetName(sheet.getName())
                .build();
    }
}