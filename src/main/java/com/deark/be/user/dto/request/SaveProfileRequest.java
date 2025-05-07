package com.deark.be.user.dto.request;

import com.deark.be.user.domain.type.Gender;

import java.time.LocalDate;

public record SaveProfileRequest(
        String nickname,
        Gender gender,
        LocalDate birthDate
) {
}
