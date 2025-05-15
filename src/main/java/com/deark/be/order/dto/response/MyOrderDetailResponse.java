package com.deark.be.order.dto.response;

import com.deark.be.order.domain.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Map;

@Builder
public record MyOrderDetailResponse(
        @Schema(description = "주문서 ID", example = "1")
        Long messageId,
        @Schema(description = "운영 시간", example = "11:00 ~ 20:00")
        String operatingHours,
        @Schema(description = "디자인 이름", example = "루피 케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://deark.com/design/1/image")
        String designImageUrl,
        @Schema(description = "주문서 질문 & 답", example = "{\"name\": {\"answer\": \"김혜연\"}}")
        Map<String, Map<String, Object>> qaDetails
) {
    public static MyOrderDetailResponse of(Message message, String operatingHours, Map<String, Map<String, Object>> qaMap) {
        return MyOrderDetailResponse.builder()
                .messageId(message.getId())
                .operatingHours(operatingHours)
                .designName(message.getDesignName())
                .designImageUrl(message.getDesignImageUrl())
                .qaDetails(qaMap)
                .build();
    }
}