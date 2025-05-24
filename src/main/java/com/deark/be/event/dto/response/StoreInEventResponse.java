package com.deark.be.event.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

@Builder
public record StoreInEventResponse(
        @Schema(description = "스토어 ID", example = "10")
        Long storeId,
        @Schema(description = "스토어 이름", example = "달콤한 케이크점")
        String storeName,
        @Schema(description = "스토어 주소", example = "서울특별시 마포구 양화로 45")
        String storeAddress,
        @Schema(description = "해당 스토어에 대한 메모", example = "추천받은 가게")
        String memo,
        @Schema(description = "스토어 디자인 URL 리스트(최대 4개)")
        List<String> designImageUrls

) {
}
