package com.deark.be.design.repository;

import com.deark.be.design.dto.response.SearchDesignResponse;
import com.deark.be.store.domain.type.BusinessDay;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

import static com.deark.be.design.domain.QDesign.design;
import static com.deark.be.store.domain.QBusinessHours.businessHours;
import static com.deark.be.store.domain.QStore.store;
import static com.deark.be.design.domain.QSize.size;

@Repository
@RequiredArgsConstructor
public class DesignRepositoryImpl implements DesignRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SearchDesignResponse> findAllDesignByCriteria(String keyword, Boolean isSameDayOrder, List<String> locationList,
                                                              LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice,
                                                              Boolean isUnmanned, String isLunchBoxCake) {

        List<BusinessDay> businessDays = null;

        if (!ObjectUtils.isEmpty(startDate) && !ObjectUtils.isEmpty(endDate)) {
            businessDays = getBusinessDaysBetween(startDate, endDate);
        }

        return jpaQueryFactory.select(
                        Projections.constructor(SearchDesignResponse.class,
                                design.id,
                                design.name,
                                design.store.name,
                                design.price,
                                design.store.address,
                                design.store.isSameDayOrder
                        )
                )
                .from(design)
                .join(design.store, store)
                .leftJoin(store.businessHoursList, businessHours)
                .leftJoin(store.sizeList, size)
                .where(
                        keywordSearchExpression(keyword),
                        isSameDayOrder != null ? store.isSameDayOrder.eq(isSameDayOrder) : null,
                        locationList != null && !locationList.isEmpty() ? store.address.in(locationList) : null,
                        priceBetweenExpression(minPrice, maxPrice),
                        isUnmanned != null ? store.isUnmanned.eq(isUnmanned) : null,
                        businessDays != null && !businessDays.isEmpty()
                                ? businessHours.businessDay.in(businessDays)
                                : null,
                        isLunchBoxCake != null
                                ? size.name.contains("도시락 케이크")
                                : null
                )
                .distinct()
                .fetch();
    }

    private BooleanExpression keywordSearchExpression(String keyword) {
        if (StringUtils.hasText(keyword)) {
            return design.description.contains(keyword).or(design.store.address.contains(keyword));
        } else {
            return null;
        }
    }

    private List<BusinessDay> getBusinessDaysBetween(LocalDate startDate, LocalDate endDate) {
        Set<BusinessDay> days = new HashSet<>();
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            days.add(BusinessDay.fromDayOfWeek(date.getDayOfWeek()));
            date = date.plusDays(1);
        }

        return new ArrayList<>(days);
    }

    private BooleanExpression priceBetweenExpression(Long minPrice, Long maxPrice) {
        if (minPrice != null && maxPrice != null) {
            return design.price.between(minPrice, maxPrice);
        } else if (minPrice != null) {
            return design.price.goe(minPrice);
        } else if (maxPrice != null) {
            return design.price.loe(maxPrice);
        }
        return null;
    }
}
