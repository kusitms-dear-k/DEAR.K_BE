package com.deark.be.event.repository;

import com.deark.be.event.dto.response.StoreInEventResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import static com.deark.be.design.domain.QCakeDesign.cakeDesign;
import static com.deark.be.store.domain.QStore.store;
import static com.deark.be.event.domain.QEventStore.eventStore;
import static com.deark.be.event.domain.QEvent.event;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EventStoreRepositoryImpl implements EventStoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StoreInEventResponse> findStoresInEventWithDesignImages(Long eventId, Long userId) {
        NumberTemplate<Long> distanceExpr = distanceTemplate(store.location, event.location); // 반드시 변수화

        List<Tuple> tuples = queryFactory
                .select(
                        store.id,
                        store.name,
                        store.address,
                        eventStore.memo,
                        distanceExpr, // 여기서 동일 인스턴스 사용
                        cakeDesign.imageUrl
                )
                .from(eventStore)
                .join(eventStore.event, event)
                .join(eventStore.store, store)
                .leftJoin(store.cakeDesignList, cakeDesign)
                .where(
                        event.id.eq(eventId),
                        event.user.id.eq(userId)
                )
                .orderBy(cakeDesign.id.asc())
                .fetch();

        Map<Long, StoreInEventResponse> resultMap = new LinkedHashMap<>();

        for (Tuple tuple : tuples) {
            Long storeId = tuple.get(store.id);
            String storeName = tuple.get(store.name);
            String address = tuple.get(store.address);
            String memo = tuple.get(eventStore.memo);
            String imageUrl = tuple.get(cakeDesign.imageUrl);
            Long distance = tuple.get(distanceExpr); // 여기서도 동일 인스턴스 사용

            resultMap.compute(storeId, (id, dto) -> {
                if (dto == null) {
                    log.info("Store {}: distance = {}", storeId, distance);

                    return StoreInEventResponse.builder()
                            .storeId(storeId)
                            .storeName(storeName)
                            .storeAddress(address)
                            .memo(memo)
                            .designImageUrls(imageUrl != null ? new ArrayList<>(List.of(imageUrl)) : new ArrayList<>())
                            .distance(distance)
                            .build();
                } else if (imageUrl != null && dto.designImageUrls().size() < 4) {
                    dto.designImageUrls().add(imageUrl);
                }
                return dto;
            });
        }

        return new ArrayList<>(resultMap.values());
    }

    private NumberTemplate<Long> distanceTemplate(ComparablePath<Point> storeLoc, ComparablePath<Point> eventLoc) {
        return Expressions.numberTemplate(
                Long.class,
                "ST_Distance(ST_Transform({0}, 3857), ST_Transform({1}, 3857))",
                storeLoc,
                eventLoc
        );
    }

}
