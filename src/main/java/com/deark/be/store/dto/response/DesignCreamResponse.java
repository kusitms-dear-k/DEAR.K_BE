package com.deark.be.store.dto.response;

import com.deark.be.design.domain.Cream;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record DesignCreamResponse(
        @Schema(description = "크림 맛", example = "생크림")
        String creamName
) {
    public static DesignCreamResponse from(Cream cream) {
        return DesignCreamResponse.builder()
                .creamName(cream.getName())
                .build();
    }
}