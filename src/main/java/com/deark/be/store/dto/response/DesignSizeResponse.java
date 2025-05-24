package com.deark.be.store.dto.response;

import com.deark.be.design.domain.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record DesignSizeResponse(
        @Schema(description = "디자인 사이즈", example = "도시락 케이크")
        String sizeName
) {
    public static DesignSizeResponse from(Size size) {
        return DesignSizeResponse.builder()
                .sizeName(size.getName())
                .build();
    }
}