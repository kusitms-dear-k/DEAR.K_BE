package com.deark.be.design.repository;

import com.deark.be.design.dto.response.SearchDesignResponse;

import java.time.LocalDate;
import java.util.List;

public interface DesignRepositoryCustom {
    List<SearchDesignResponse> findAllDesignByCriteria(
            String keyword, Boolean isSameDayOrder, List<String> locationList,
            LocalDate startDate, LocalDate endDate, List<Long> priceList,
            Boolean isUnmanned, String isLunchBoxCake);
}
