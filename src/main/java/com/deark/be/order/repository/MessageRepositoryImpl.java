package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.type.MakeStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.deark.be.order.domain.QOrderRequestForm.orderRequestForm;
import static com.deark.be.order.domain.QOrderRequestFormQa.orderRequestFormQa;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderRequestForm> findMessagesWithQAsByUserIdAndProgressStatusIn(Long userId, List<MakeStatus> statuses) {

        return jpaQueryFactory
                .selectFrom(orderRequestForm)
                .distinct()
                .leftJoin(orderRequestForm.orderRequestFormQaList, orderRequestFormQa).fetchJoin()
                .join(orderRequestForm.store).fetchJoin()
                .leftJoin(orderRequestForm.cakeDesign).fetchJoin()
                .where(
                        orderRequestForm.user.id.eq(userId),
                        orderRequestForm.makeStatus.in(statuses)
                )
                .fetch();
    }
}
