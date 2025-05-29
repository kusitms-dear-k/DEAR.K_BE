package com.deark.be.order.repository;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.ProgressStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.deark.be.order.domain.QMessage.message;
import static com.deark.be.order.domain.QQA.qA;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Message> findMessagesWithQAsByUserIdAndProgressStatusIn(Long userId, List<ProgressStatus> statuses) {

        return jpaQueryFactory
                .selectFrom(message)
                .distinct()
                .leftJoin(message.qaList, qA).fetchJoin()
                .join(message.store).fetchJoin()
                .leftJoin(message.design).fetchJoin()
                .where(
                        message.user.id.eq(userId),
                        message.progressStatus.in(statuses)
                )
                .fetch();
    }
}
