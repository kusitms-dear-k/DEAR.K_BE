package com.deark.be.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoInfoResponse(
        String id,
        KakaoAccount kakaoAccount
) implements OAuthInfoResponse {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    public record KakaoAccount(
            KakaoUserProfile profile,
            String email,
            String name,
            String phoneNumber
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record KakaoUserProfile(
                String nickname,
                String profileImage
        ) {
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNickname() {
        return kakaoAccount.profile().nickname();
    }

    @Override
    public String getProfileImage() {
        return kakaoAccount.profile().profileImage();
    }

    @Override
    public String getEmail() {
        return kakaoAccount.email();
    }

    @Override
    public String getName() {
        return kakaoAccount.name();
    }

    @Override
    public String getPhoneNumber() {
        return kakaoAccount.phoneNumber();
    }
}