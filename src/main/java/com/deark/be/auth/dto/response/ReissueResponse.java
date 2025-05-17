package com.deark.be.auth.dto.response;

import lombok.Builder;

@Builder
public record ReissueResponse(
        String accessToken
) {
    public static ReissueResponse from(String accessToken) {
        return ReissueResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}