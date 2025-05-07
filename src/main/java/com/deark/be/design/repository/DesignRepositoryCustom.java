package com.deark.be.design.repository;

import com.deark.be.design.dto.response.SearchDesignPagedResult;
import com.deark.be.design.dto.response.StoreDesignResponse;
import com.deark.be.store.domain.type.SortType;

import java.time.LocalDate;
import java.util.List;

public interface DesignRepositoryCustom {
    SearchDesignPagedResult findAllDesignByCriteria(
            Long userId, Long page, Long count, SortType sortType,
            String keyword, Boolean isSameDayOrder, List<String> locationList,
            LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice, Boolean isLunchBoxCake);

    List<StoreDesignResponse> findAllDesignBySizeAndStoreId(Long userId, Long page, Long count, Long storeId, String sizeName);
}
