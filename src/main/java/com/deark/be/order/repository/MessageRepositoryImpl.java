package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.type.ProgressStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.deark.be.order.domain.QOrderRequestForm.orderRequestForm;
import static com.deark.be.order.domain.QQA.qA;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderRequestForm> findMessagesWithQAsByUserIdAndProgressStatusIn(Long userId, List<ProgressStatus> statuses) {

        return jpaQueryFactory
                .selectFrom(orderRequestForm)
                .distinct()
                .leftJoin(orderRequestForm.qaList, qA).fetchJoin()
                .join(orderRequestForm.store).fetchJoin()
                .leftJoin(orderRequestForm.cakeDesign).fetchJoin()
                .where(
                        orderRequestForm.user.id.eq(userId),
                        orderRequestForm.progressStatus.in(statuses)
                )
                .fetch();
    }
}
