package com.deark.be.user.dto.request;

import com.deark.be.user.domain.type.Role;

public record UpdateRoleRequest(
        Role role
) {
}