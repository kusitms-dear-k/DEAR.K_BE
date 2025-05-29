package com.deark.be.order.dto.response;

import com.deark.be.order.domain.QA;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record QAResponse(
        String title,
        String answer,
        Boolean isRequired,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String requestDetailImageUrl
) {
    public static QAResponse from(QA qa) {
        return QAResponse.builder()
                .title(qa.getQuestion())
                .answer(qa.getAnswer())
                .isRequired(qa.getIsRequired())
                .requestDetailImageUrl("추가 요청사항".equals(qa.getQuestion()) ? qa.getMessage().getRequestDetailImageUrl() : null)
                .build();
    }
}

