package com.deark.be.store.repository;

import com.deark.be.store.domain.type.SortType;
import com.deark.be.store.dto.response.SearchStorePagedResult;

import java.time.LocalDate;
import java.util.List;

public interface StoreRepositoryCustom {
    SearchStorePagedResult findAllStoreByCriteria(
            Long userId, Long page, Long count, SortType sortType,
            String keyword, Boolean isSameDayOrder, List<String> locationList,
            LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice,
            Boolean isSelfService, Boolean isLunchBoxCake);
}
