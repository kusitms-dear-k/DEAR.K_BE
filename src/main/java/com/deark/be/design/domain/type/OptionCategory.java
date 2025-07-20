package com.deark.be.design.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OptionCategory {
    SIZE("사이즈"),
    CREAM("크림"),
    SHEET("시트"),
    ETC("기타"),
    ;

    private final String description;
}
