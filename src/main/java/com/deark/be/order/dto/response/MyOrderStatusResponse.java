package com.deark.be.order.dto.response;

import com.deark.be.order.domain.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record MyOrderStatusResponse(
        @Schema(description = "쪽지 ID", example = "1")
        Long messageId,
        @Schema(description = "요청 날짜", example = "2025-05-01")
        LocalDate createdAt,
        @Schema(description = "가게 이름", example = "디어케이 케이크")
        String storeName,
        @Schema(description = "디자인 이름", example = "루피 케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://deark.com/design/1/image")
        String designImageUrl,
        @Schema(description = "주문서 질문 & 답", example = "{ \"크기\": \"도시락 케이크\", \"픽업 희망 날짜\": \"2025년 6월 20일 금요일\", \"픽업 희망 시간\": \"13시 30분\" }")
        Map<String, String> qaDetails
) {
    public static MyOrderStatusResponse of(Message message, Map<String, String> qaMap) {
        return MyOrderStatusResponse.builder()
                .messageId(message.getId())
                .createdAt(message.getCreatedAt().toLocalDate())
                .storeName(message.getStore().getName())
                .designName(message.getDesignName())
                .designImageUrl(message.getDesignImageUrl())
                .qaDetails(qaMap)
                .build();
    }
}