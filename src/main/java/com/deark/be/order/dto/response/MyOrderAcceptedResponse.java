package com.deark.be.order.dto.response;

import com.deark.be.order.domain.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Map;

@Builder
public record MyOrderAcceptedResponse(
        @Schema(description = "메이커 답변", example = "안녕하세요 고객님 :) 문의해주셔서 감사합니다. 해당 디자인으로 말씀하신 일정에 가능합니다!")
        String makerResponse,
        @Schema(description = "카카오톡 채팅 URL", example = "https://kakao.com/chat/123456789")
        String kakaoChattingUrl,
        @Schema(description = "주문서 질문 & 답", example = "{ \"크기\": \"도시락 케이크\", \"픽업 희망 날짜\": \"2025년 6월 20일 금요일\", \"픽업 희망 시간\": \"13시 30분\" }")
        Map<String, String> qaDetails
) {
    public static MyOrderAcceptedResponse of(Message message, Map<String, String> qaMap) {
        return MyOrderAcceptedResponse.builder()
                .makerResponse(message.getMakerResponse())
                .kakaoChattingUrl(message.getStore().getChattingUrl())
                .qaDetails(qaMap)
                .build();
    }
}