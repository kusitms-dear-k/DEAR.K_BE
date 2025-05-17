package com.deark.be.order.dto.response;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.DesignType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record MyOrderDetailResponse(
        @Schema(description = "주문서 ID", example = "1")
        Long messageId,
        @Schema(description = "가게 운영 시간", example = "11:00 ~ 20:00")
        String operatingHours,
        @Schema(description = "가게 카카오톡 URL", example = "https://kakao.com/deark")
        String chattingUrl,
        @Schema(description = "디자인 이름", example = "루피 케이크")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://deark.com/design/1/image")
        String designImageUrl,
        @Schema(description = "디자인 타입", example = "STORE")
        DesignType designType,
        @Schema(description = "주문서 질문 & 답", example = "[{\"title\": \"이름\", \"answer\": \"박지유\", \"isRequired\": \"true\"}]")
        List<QAResponse> qaDetails
) {
    public static MyOrderDetailResponse of(Message message, String operatingHours, List<QAResponse> qaDetails) {
        return MyOrderDetailResponse.builder()
                .messageId(message.getId())
                .operatingHours(operatingHours)
                .chattingUrl(message.getStore().getChattingUrl())
                .designName(message.getDesignName())
                .designImageUrl(message.getDesignImageUrl())
                .designType(message.getDesignType())
                .qaDetails(qaDetails)
                .build();
    }
}