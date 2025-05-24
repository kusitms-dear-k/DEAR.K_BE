package com.deark.be.order.dto.response;

import com.deark.be.order.domain.QA;
import lombok.Builder;

@Builder
public record QAStatusResponse(
        String title,
        String answer
) {
    public static QAStatusResponse from(QA qa) {
        return QAStatusResponse.builder()
                .title(qa.getQuestion())
                .answer(qa.getAnswer())
                .build();
    }
}
