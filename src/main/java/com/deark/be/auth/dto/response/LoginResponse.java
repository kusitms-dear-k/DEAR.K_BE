package com.deark.be.auth.dto.response;

import com.deark.be.user.domain.User;
import com.deark.be.user.domain.type.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginResponse(
        @Schema(description = "사용자 권한", example = "ADMIN")
        Role role,
        @Schema(description = "사용자 ID", example = "1")
        Long userId,
        @Schema(description = "프로필 이미지 URL", example = "https://cdn.deark.com/users/profile.png")
        String profileImageUrl,
        @Schema(description = "사용자 닉네임", example = "케이크광인")
        String nickname,
        @Schema(description = "사용자 전화번호", example = "010-1234-5678")
        String phoneNumber
) {
    public static LoginResponse from(User user) {
        return LoginResponse.builder()
                .role(user.getRole())
                .userId(user.getId())
                .profileImageUrl(user.getProfileImageUrl())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhone())
                .build();
    }
}