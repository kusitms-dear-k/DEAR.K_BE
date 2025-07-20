package com.deark.be.store.dto.response;

import java.util.List;

public record CakeDesignOptionResponseList(
        List<CakeDesignOptionResponse> cakeDesignOptionList
) {
    public static CakeDesignOptionResponseList from(List<CakeDesignOptionResponse> cakeDesignOptionList) {
        return new CakeDesignOptionResponseList(cakeDesignOptionList);
    }
}
