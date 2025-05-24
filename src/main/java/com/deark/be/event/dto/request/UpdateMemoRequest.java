package com.deark.be.event.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;


public record UpdateMemoRequest(
        @Schema(description = "메모", example = "내가 픽한 거!!")
        String memo
) {
}
