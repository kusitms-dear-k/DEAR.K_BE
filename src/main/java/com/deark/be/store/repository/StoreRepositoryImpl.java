package com.deark.be.store.repository;

import com.deark.be.store.domain.type.BusinessDay;
import com.deark.be.store.domain.type.SortType;
import com.deark.be.store.dto.response.SearchStorePagedResult;
import com.deark.be.store.dto.response.SearchStoreResponse;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

import static com.deark.be.design.domain.QDesign.design;
import static com.deark.be.design.domain.QSize.size;
import static com.deark.be.event.domain.QEvent.event;
import static com.deark.be.event.domain.QEventStore.eventStore;
import static com.deark.be.store.domain.QBusinessHours.businessHours;
import static com.deark.be.store.domain.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public SearchStorePagedResult findAllStoreByCriteria(
            Long userId, Long page, Long count, SortType sortType,
            String keyword, Boolean isSameDayOrder, List<String> locationList,
            LocalDate startDate, LocalDate endDate,
            Long minPrice, Long maxPrice, Boolean isLunchBoxCake) {

        BooleanExpression keywordExpr  = keywordSearchExpression(keyword);
        BooleanExpression sameDayExpr  = isSameDayOrder != null
                ? store.isSameDayOrder.eq(isSameDayOrder)
                : null;
        BooleanExpression locationExpr = locationListExpression(locationList);
        BooleanExpression priceExpr    = priceBetweenExpression(minPrice, maxPrice);

        List<BusinessDay> businessDays =
                (startDate != null && endDate != null)
                        ? getBusinessDayList(startDate, endDate)
                        : Collections.emptyList();
        BooleanExpression businessDayExpr = !businessDays.isEmpty()
                ? businessHours.businessDay.in(businessDays)
                : null;

        BooleanExpression hasLunchBoxCakeSizeExpr = JPAExpressions
                .selectOne()
                .from(size)
                .join(size.design, design)
                .where(design.store.eq(store)
                        .and(size.name.contains("도시락")))
                .exists();

        BooleanExpression isLikedExpr = (userId != null && userId != 0L)
                ? JPAExpressions
                .selectOne()
                .from(eventStore)
                .join(eventStore.event, event)
                .where(event.user.id.eq(userId)
                        .and(eventStore.store.eq(store)))
                .exists()
                : Expressions.FALSE;

        Predicate filter = ExpressionUtils.allOf(
                keywordExpr,
                sameDayExpr,
                locationExpr,
                priceExpr,
                businessDayExpr,
                Boolean.TRUE.equals(isLunchBoxCake)
                        ? hasLunchBoxCakeSizeExpr
                        : null
        );

        Long total = jpaQueryFactory
                .select(store.id.countDistinct())
                .from(store)
                .leftJoin(store.businessHoursList, businessHours)
                .where(filter)
                .fetchOne();

        if (total == null) total = 0L;

        List<Long> pagedIds = jpaQueryFactory
                .selectDistinct(store.id)
                .from(store)
                .leftJoin(store.businessHoursList, businessHours)
                .where(filter)
                .orderBy(
                        SortType.LATEST.equals(sortType)
                                ? store.id.desc()
                                : store.id.asc()
                )
                .offset(page * count)
                .limit(count)
                .fetch();

        boolean hasNext = (page + 1) * count < total;

        Map<Long, SearchStoreResponse> resultMap = jpaQueryFactory
                .from(store)
                .leftJoin(store.designList, design)
                .where(store.id.in(pagedIds))
                .transform(
                        GroupBy.groupBy(store.id).as(
                                Projections.constructor(
                                        SearchStoreResponse.class,
                                        store.id,
                                        store.name,
                                        store.imageUrl,
                                        store.address,
                                        store.isSameDayOrder,
                                        store.isUnmanned,
                                        hasLunchBoxCakeSizeExpr,
                                        isLikedExpr,
                                        GroupBy.list(design.imageUrl)
                                )
                        )
                );

        List<SearchStoreResponse> content = pagedIds.stream()
                .map(resultMap::get)
                .toList();

        return SearchStorePagedResult.of(total, hasNext, content);
    }


    private List<BusinessDay> getBusinessDayList(LocalDate startDate, LocalDate endDate) {
        List<BusinessDay> businessDays = null;

        if (!ObjectUtils.isEmpty(startDate) && !ObjectUtils.isEmpty(endDate)) {
            businessDays = getBusinessDaysBetween(startDate, endDate);
        }

        return businessDays;
    }

    private BooleanExpression keywordSearchExpression(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            return null;
        }

        String[] tokens = keyword.trim().split("\\s+");

        BooleanExpression expr = null;

        for (String token : tokens) {
            BooleanExpression tokenExpr = store.name.contains(token)
                    .or(store.description.contains(token))
                    .or(store.address.contains(token))
                    .or(store.designList.any().description.contains(token));

            expr = (expr == null) ? tokenExpr : expr.or(tokenExpr);
        }

        return expr;
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
            return store.designList.any().price.between(minPrice, maxPrice);
        } else if (minPrice != null) {
            return store.designList.any().price.goe(minPrice);
        } else if (maxPrice != null) {
            return store.designList.any().price.loe(maxPrice);
        }

        return null;
    }

    private BooleanExpression locationListExpression(List<String> locationList) {
        if (ObjectUtils.isEmpty(locationList)) {
            return null;
        }

        BooleanExpression expr = null;

        for (String loc : locationList) {
            BooleanExpression next = store.address.contains(loc);
            expr = (expr == null) ? next : expr.or(next);
        }

        return expr;
    }
}
