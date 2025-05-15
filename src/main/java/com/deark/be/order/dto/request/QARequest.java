package com.deark.be.order.dto.request;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주문서 질문과 답변에 대한 정보")
public record QARequest(
        @Schema(description = "질문", example = "이름")
        String title,

        @Schema(description = "답변", example = "김혜연")
        String answer
) {
    public QA toEntity(Message message) {
        return QA.builder()
                .message(message)
                .question(title)
                .answer(answer)
                .build();
    }
}