package com.deark.be.user.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    OWNER("ROLE_OWNER"),
    CUSTOMER("ROLE_CUSTOMER"),
    ;

    private final String authority;

    public Collection<? extends GrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.authority));
    }
}