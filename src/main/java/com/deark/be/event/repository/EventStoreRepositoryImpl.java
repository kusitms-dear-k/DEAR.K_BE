package com.deark.be.event.repository;

import com.deark.be.event.dto.response.StoreInEventResponse;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.deark.be.design.domain.QCakeDesign.cakeDesign;
import static com.deark.be.store.domain.QStore.store;
import static com.deark.be.event.domain.QEventStore.eventStore;
import static com.deark.be.event.domain.QEvent.event;

@Repository
@RequiredArgsConstructor
public class EventStoreRepositoryImpl implements EventStoreRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StoreInEventResponse> findStoresInEventWithDesignImages(Long eventId, Long userId) {
        List<Tuple> tuples = queryFactory
                .select(
                        store.id,
                        store.name,
                        store.address,
                        eventStore.memo,
                        //TODO: 거리 계산 로직 추가 필요
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
                .orderBy(cakeDesign.id.asc()) // 디자인 순서는 커스터마이징 가능
                .fetch();

        // storeId -> StoreInEventResponse
        Map<Long, StoreInEventResponse> resultMap = new LinkedHashMap<>();

        for (Tuple tuple : tuples) {
            Long storeId = tuple.get(store.id);
            String storeName = tuple.get(store.name);
            String address = tuple.get(store.address);
            String memo = tuple.get(eventStore.memo);
            String imageUrl = tuple.get(cakeDesign.imageUrl);

            resultMap.compute(storeId, (id, dto) -> {
                if (dto == null) {
                    return StoreInEventResponse.builder()
                            .storeId(storeId)
                            .storeName(storeName)
                            .storeAddress(address)
                            .memo(memo)
                            .designImageUrls(imageUrl != null ? new ArrayList<>(List.of(imageUrl)) : new ArrayList<>())
                            .build();
                } else if (imageUrl != null && dto.designImageUrls().size() < 4) {
                    dto.designImageUrls().add(imageUrl);
                }
                return dto;
            });
        }

        return new ArrayList<>(resultMap.values());
    }
}
