package com.deark.be.design.repository;

import com.deark.be.design.dto.response.DesignDetailResponse;
import com.deark.be.design.dto.response.SearchDesignPagedResult;
import com.deark.be.design.dto.response.SearchDesignResponse;
import com.deark.be.design.dto.response.StoreDesignResponse;
import com.deark.be.store.domain.type.BusinessDay;
import com.deark.be.store.domain.type.SortType;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

import static com.deark.be.design.domain.QCream.cream;
import static com.deark.be.design.domain.QDesign.design;
import static com.deark.be.design.domain.QSheet.sheet;
import static com.deark.be.design.domain.QSize.size;
import static com.deark.be.event.domain.QEvent.event;
import static com.deark.be.event.domain.QEventDesign.eventDesign;
import static com.deark.be.store.domain.QBusinessHours.businessHours;
import static com.deark.be.store.domain.QStore.store;
import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class DesignRepositoryImpl implements DesignRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public SearchDesignPagedResult findAllDesignByCriteria(
            Long userId, Long page, Long count, SortType sortType,
            String keyword, Boolean isSameDayOrder, List<String> locationList,
            LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice, Boolean isLunchBoxCake) {

        // --- 1) 공통 표현식들 ---
        BooleanExpression keywordExpr  = keywordSearchExpression(keyword);
        BooleanExpression sameDayExpr  = (isSameDayOrder != null) ? store.isSameDayOrder.eq(isSameDayOrder) : null;
        BooleanExpression locationExpr = locationListExpression(locationList);
        BooleanExpression priceExpr    = priceBetweenExpression(minPrice, maxPrice);

        List<BusinessDay> businessDays =
                (startDate != null && endDate != null)
                        ? getBusinessDayList(startDate, endDate)
                        : Collections.emptyList();
        BooleanExpression businessDayExpr = (!businessDays.isEmpty())
                ? businessHours.businessDay.in(businessDays)
                : null;

        BooleanExpression hasLunchBoxCakeSizeExpr = JPAExpressions
                .selectOne()
                .from(size)
                .join(size.design, design)
                .where(design.store.eq(store)
                        .and(size.name.contains("도시락")))
                .exists();

        NumberExpression<Long> likeCount = eventDesign.id.countDistinct().coalesce(0L);

        NumberExpression<Long> likedSum = Expressions.numberTemplate(
                Long.class,
                "SUM(CASE WHEN {0} THEN 1 ELSE 0 END)",
                event.user.id.eq(userId).and(eventDesign.design.eq(design))
        );

        BooleanExpression isLikedExpr = likedSum.gt(0L);

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
                        likeCount
                ))
                .from(design)
                .join(design.store, store)
                .leftJoin(design.eventDesignList, eventDesign)
                .leftJoin(eventDesign.event, event)
                .leftJoin(store.businessHoursList, businessHours)
                .where(
                        keywordExpr,
                        sameDayExpr,
                        locationExpr,
                        priceExpr,
                        businessDayExpr,
                        Boolean.TRUE.equals(isLunchBoxCake)
                                ? hasLunchBoxCakeSizeExpr
                                : null
                )
                .groupBy(
                        design.id,
                        design.name,
                        design.imageUrl,
                        store.name,
                        design.price,
                        store.address,
                        store.isSameDayOrder
                )
                .offset(page * count)
                .limit(count + 1);

        if (SortType.LATEST.equals(sortType)) {
            contentQuery.orderBy(design.id.desc());
        } else if (SortType.POPULARITY.equals(sortType)) {
            contentQuery.orderBy(likeCount.desc());
        }

        List<SearchDesignResponse> content = contentQuery.fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(design.id.countDistinct())
                .from(design)
                .join(design.store, store)
                .leftJoin(store.businessHoursList, businessHours)
                .where(
                        keywordExpr,
                        sameDayExpr,
                        locationExpr,
                        priceExpr,
                        businessDayExpr,
                        Boolean.TRUE.equals(isLunchBoxCake)
                                ? hasLunchBoxCakeSizeExpr
                                : null
                );

        Long total = ofNullable(countQuery.fetchOne()).orElse(0L);

        return SearchDesignPagedResult.of(total, content);
    }

    @Override
    public List<StoreDesignResponse> findAllDesignBySizeAndStoreId(Long userId, Long page, Long count, Long storeId, String sizeName) {
        NumberExpression<Long> likedSum = Expressions.numberTemplate(
                Long.class,
                "SUM(CASE WHEN {0} THEN 1 ELSE 0 END)",
                event.user.id.eq(userId).and(eventDesign.design.eq(design))
        );

        BooleanExpression isLikedExpr = likedSum.gt(0L);

        BooleanExpression sizeFilter = (sizeName != null && !sizeName.isBlank())
                ? design.sizeList.any().name.eq(sizeName)
                : null;

        return jpaQueryFactory
                .select(Projections.constructor(
                        StoreDesignResponse.class,
                        design.id,
                        design.name,
                        design.imageUrl,
                        store.name,
                        design.price,
                        isLikedExpr
                ))
                .from(design)
                .join(design.store, store)
                .leftJoin(design.eventDesignList, eventDesign)
                .leftJoin(eventDesign.event, event)
                .where(
                        design.store.id.eq(storeId),
                        sizeFilter
                )
                .groupBy(
                        design.id,
                        design.name,
                        design.imageUrl,
                        store.name,
                        design.price
                )
                .offset(page * count)
                .limit(count + 1)
                .fetch();
    }

    @Override
    public DesignDetailResponse findDesignDetailById(Long userId, Long designId) {
        Boolean isLiked = jpaQueryFactory
                .selectOne()
                .from(eventDesign)
                .join(eventDesign.event, event)
                .where(
                        event.user.id.eq(userId),
                        eventDesign.design.id.eq(designId)
                )
                .fetchFirst() != null;

        Long likeCount = jpaQueryFactory
                .select(eventDesign.count())
                .from(eventDesign)
                .where(eventDesign.design.id.eq(designId))
                .fetchOne();

        Tuple result = jpaQueryFactory
                .select(
                        design.store.name,
                        design.name,
                        design.imageUrl,
                        design.description,
                        design.price
                )
                .from(design)
                .where(design.id.eq(designId))
                .fetchOne();

        if (result == null) {
            return null;
        }

        List<String> sizeList = jpaQueryFactory
                .select(size.name)
                .from(size)
                .where(size.design.id.eq(designId))
                .fetch();

        List<String> creamList = jpaQueryFactory
                .select(cream.name)
                .from(cream)
                .where(cream.design.id.eq(designId))
                .fetch();

        List<String> sheetList = jpaQueryFactory
                .select(sheet.name)
                .from(sheet)
                .where(sheet.design.id.eq(designId))
                .fetch();

        return DesignDetailResponse.builder()
                .storeName(result.get(design.store.name))
                .designName(result.get(design.name))
                .designImageUrl(result.get(design.imageUrl))
                .description(result.get(design.description))
                .price(result.get(design.price))
                .isLiked(isLiked)
                .likeCount(likeCount)
                .sizeList(sizeList)
                .creamList(creamList)
                .sheetList(sheetList)
                .build();
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
