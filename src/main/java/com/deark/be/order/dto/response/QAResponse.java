package com.deark.be.order.dto.response;

import com.deark.be.order.domain.QA;
import lombok.Builder;

@Builder
public record QAResponse(
        String title,
        String answer,
        Boolean isRequired
) {
    public static QAResponse from(QA qa) {
        return QAResponse.builder()
                .title(qa.getQuestion())
                .answer(qa.getAnswer())
                .isRequired(qa.getIsRequired())
                .build();
    }
}
