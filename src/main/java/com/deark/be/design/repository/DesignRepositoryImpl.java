package com.deark.be.design.repository;

import com.deark.be.design.dto.response.SearchDesignPagedResult;
import com.deark.be.design.dto.response.SearchDesignResponse;
import com.deark.be.store.domain.type.BusinessDay;
import com.deark.be.store.domain.type.SortType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
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
import static com.deark.be.event.domain.QEventDesign.eventDesign;
import static com.deark.be.store.domain.QBusinessHours.businessHours;
import static com.deark.be.store.domain.QStore.store;

@Repository
@RequiredArgsConstructor
public class DesignRepositoryImpl implements DesignRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public SearchDesignPagedResult findAllDesignByCriteria(
            Long userId, Long page, Long count, SortType sortType,
            String keyword, Boolean isSameDayOrder, List<String> locationList,
            LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice, Boolean isLunchBoxCake) {

        BooleanExpression keywordExpr  = keywordSearchExpression(keyword);
        BooleanExpression sameDayExpr  = (isSameDayOrder != null) ? store.isSameDayOrder.eq(isSameDayOrder) : null;
        BooleanExpression locationExpr = locationListExpression(locationList);
        BooleanExpression priceExpr    = priceBetweenExpression(minPrice, maxPrice);

        List<BusinessDay> businessDays = (startDate != null && endDate != null)
                ? getBusinessDayList(startDate, endDate)
                : Collections.emptyList();
        BooleanExpression businessDayExpr = (!businessDays.isEmpty())
                ? businessHours.businessDay.in(businessDays)
                : null;

        BooleanExpression isLikedExpr = (userId != null && userId != 0L)
                ? JPAExpressions
                .selectOne()
                .from(eventDesign)
                .join(eventDesign.event, event)
                .where(event.user.id.eq(userId)
                        .and(eventDesign.design.eq(design)))
                .exists()
                : Expressions.FALSE;

        JPAQuery<SearchDesignResponse> contentQuery = jpaQueryFactory
                .select(Projections.constructor(
                        SearchDesignResponse.class,
                        design.id,
                        design.name,
                        design.imageUrl,
                        store.name,
                        design.price,
                        store.address,
                        store.isSameDayOrder,
                        isLikedExpr,
                        eventDesign.id.count().coalesce(0L)
                ))
                .from(design)
                .join(design.store, store)
                .leftJoin(eventDesign).on(eventDesign.design.eq(design))
                .on(Boolean.TRUE.equals(isLunchBoxCake)
                        ? size.design.eq(design).and(size.name.contains("도시락"))
                        : Expressions.TRUE)
                .where(keywordExpr, sameDayExpr, locationExpr, priceExpr, businessDayExpr)
                .groupBy(
                        design.id, design.name, design.imageUrl,
                        store.name, design.price, store.address, store.isSameDayOrder,
                        isLikedExpr
                )
                .offset(page * count)
                .limit(count + 1);

        if (SortType.LATEST.equals(sortType)) {
            contentQuery.orderBy(design.id.desc());
        } else if (SortType.POPULARITY.equals(sortType)) {
            contentQuery.orderBy(eventDesign.id.count().desc());
        }

        List<SearchDesignResponse> content = contentQuery.fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(design.id.countDistinct())
                .from(design)
                .join(design.store, store)
                .leftJoin(store.businessHoursList, businessHours);

        if (Boolean.TRUE.equals(isLunchBoxCake)) {
            countQuery.innerJoin(size)
                    .on(size.design.eq(design)
                            .and(size.name.contains("도시락")));
        }

        Long total = countQuery
                .where(
                        keywordExpr,
                        sameDayExpr,
                        locationExpr,
                        priceExpr,
                        businessDayExpr
                )
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return SearchDesignPagedResult.of(total, content);
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
            BooleanExpression tokenExpr = design.name.contains(token)
                    .or(design.description.contains(token))
                    .or(store.address.contains(token));

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
            return design.price.between(minPrice, maxPrice);
        } else if (minPrice != null) {
            return design.price.goe(minPrice);
        } else if (maxPrice != null) {
            return design.price.loe(maxPrice);
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
