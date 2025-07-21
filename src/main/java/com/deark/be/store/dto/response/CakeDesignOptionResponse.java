package com.deark.be.store.dto.response;

import com.deark.be.design.domain.CakeDesignOption;
import com.deark.be.design.domain.type.OptionCategory;

public record CakeDesignOptionResponse(
        OptionCategory optionCategory,
        String name
) {

    public static CakeDesignOptionResponse from(CakeDesignOption cakeDesignOption) {
        return new CakeDesignOptionResponse(cakeDesignOption.getOptionCategory(), cakeDesignOption.getValue());
    }
}
