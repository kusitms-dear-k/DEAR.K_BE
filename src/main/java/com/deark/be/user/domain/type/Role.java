package com.deark.be.user.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    OWNER("ROLE_OWNER"),
    CUSTOMER("ROLE_CUSTOMER"),
    ;

    private final String authority;
}