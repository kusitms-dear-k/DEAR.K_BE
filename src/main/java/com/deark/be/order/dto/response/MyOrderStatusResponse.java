package com.deark.be.order.dto.response;

import com.deark.be.order.domain.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Builder
public record MyOrderStatusResponse(
        @Schema(description = "쪽지 ID", example = "1")
        Long messageId,
        @Schema(description = "요청 날짜", example = "2025-05-01(목)")
        String requestDate,
        @Schema(description = "가게 이름", example = "디어케이 케이크")
        String storeName,
        @Schema(description = "디자인 이름", example = "루피 케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://deark.com/design/1/image")
        String designImageUrl,
        @Schema(description = "주문서 질문 & 답", example = "[{\"title\": \"이름\", \"answer\": \"박지유\"}]")
        List<QAStatusResponse> qaDetails
) {
    public static MyOrderStatusResponse of(Message message, List<QAStatusResponse> qaDetails) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd(E)", Locale.KOREAN);
        String formattedDate = message.getCreatedAt().format(formatter);

        return MyOrderStatusResponse.builder()
                .messageId(message.getId())
                .requestDate(formattedDate)
                .storeName(message.getStore().getName())
                .designName(message.getDesignName())
                .designImageUrl(message.getDesignImageUrl())
                .qaDetails(qaDetails)
                .build();
    }
}