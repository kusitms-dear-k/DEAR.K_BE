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
        @Schema(description = "주문서 질문 & 답", example = "{ \"크기\": \"도시락 케이크\", \"픽업 희망 날짜\": \"2025년 6월 20일 금요일\", \"픽업 희망 시간\": \"13시 30분\" }")
        Map<String, String> qaDetails
) {
    public static MyOrderDetailResponse of(Message message, String operatingHours, Map<String, String> qaMap) {
        return MyOrderDetailResponse.builder()
                .messageId(message.getId())
                .operatingHours(operatingHours)
                .designName(message.getDesignName())
                .designImageUrl(message.getDesignImageUrl())
                .qaDetails(qaMap)
                .build();
    }
}