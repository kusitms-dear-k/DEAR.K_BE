package com.deark.be.auth.service.type;

import com.deark.be.user.domain.type.Role;
import io.jsonwebtoken.Claims;
import lombok.Builder;

@Builder
public record JwtUserDetails(
        Long userId,
        Role role
) {
    public static JwtUserDetails fromClaim(Claims claims) {
        String roleString = claims.get("role").toString();
        Role role = Role.valueOf(roleString.replace("ROLE_", "")); // "ROLE_ADMIN" -> "ADMIN"

        return JwtUserDetails.builder()
                .userId(Long.valueOf(claims.getSubject()))
                .role(role)
                .build();
    }
}