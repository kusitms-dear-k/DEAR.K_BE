package com.deark.be.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record DesignCreamResponseList(
        @Schema(description = "가게에서 제공하는 크림 맛 리스트")
        List<DesignCreamResponse> creamList
) {
    public static DesignCreamResponseList from(List<DesignCreamResponse> creamList) {
        return DesignCreamResponseList.builder()
                .creamList(creamList)
                .build();
    }
}