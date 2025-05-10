package com.deark.be.event.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UpdateStoreMappingRequest(
        @Schema(description = "스토어 ID", example = "55")
        Long storeId,
        @Schema(description = "스토어를 매핑할 이벤트 ID 목록", example = "[2, 4, 5]")
        List<Long> eventIds
) {
}