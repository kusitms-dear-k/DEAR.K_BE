package com.deark.be.auth.dto.response;

import com.deark.be.user.domain.User;
import com.deark.be.user.domain.type.Role;
import lombok.Builder;

@Builder
public record LoginResponse(
        Role role
) {
    public static LoginResponse from(User user) {
        return LoginResponse.builder()
                .role(user.getRole())
                .build();
    }
}