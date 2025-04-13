package com.deark.be.user.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    HQ_USER("ROLE_OWNER"),
    CS_USER("ROLE_CUSTOMER"),
    ;

    private final String authority;
}