package com.deark.be.event.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UpdateDesignMappingRequest(
        @Schema(description = "디자인 ID", example = "1")
        Long designId,
        @Schema(description = "디자인을 매핑할 이벤트 ID 목록", example = "[1, 2, 3]")
        List<Long> eventIds
) {
}
