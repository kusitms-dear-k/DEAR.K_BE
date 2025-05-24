package com.deark.be.order.dto.response;

import com.deark.be.order.domain.CommonQuestion;
import com.deark.be.order.domain.OrderQuestion;
import com.deark.be.order.domain.type.QuestionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "가게 주문서 질문 응답 DTO")
@Builder
public record OrderQuestionResponse(
        @Schema(description = "질문 본문")
        String question,

        @Schema(description = "힌트 (COMMON일 경우에만 사용)")
        String hint,

        @Schema(description = "필수 여부")
        Boolean isRequired,

        @Schema(description = "질문 유형 (COMMON 또는 CUSTOM)")
        QuestionType questionType
) {
    public static OrderQuestionResponse from(OrderQuestion q) {
        String questionText;
        String hint;
        if (q.getQuestionType() == QuestionType.COMMON) {
            CommonQuestion cq = q.getCommonQuestion();
            questionText = cq.getTitle();
            hint = cq.getHint();
        } else {
            questionText = q.getContent(); // 커스텀은 content가 질문
            hint = null;
        }
        return OrderQuestionResponse.builder()
                .question(questionText)
                .hint(hint)
                .isRequired(q.getIsRequired())
                .questionType(q.getQuestionType())
                .build();
    }
}