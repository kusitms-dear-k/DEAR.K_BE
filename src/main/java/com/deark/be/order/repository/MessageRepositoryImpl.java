package com.deark.be.order.repository;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QMessage;
import com.deark.be.order.domain.QQA;
import com.deark.be.order.domain.type.ProgressStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Message> findMessagesWithQAsByUserIdAndProgressStatusIn(Long userId, List<ProgressStatus> statuses) {
        QMessage qMessage = QMessage.message;
        QQA qQA = QQA.qA;

        return jpaQueryFactory
                .selectFrom(qMessage)
                .distinct()
                .leftJoin(qMessage.qaList, qQA).fetchJoin()
                .join(qMessage.store).fetchJoin()
                .leftJoin(qMessage.design).fetchJoin()
                .where(
                        qMessage.user.id.eq(userId),
                        qMessage.progressStatus.in(statuses)
                )
                .fetch();
    }
}
