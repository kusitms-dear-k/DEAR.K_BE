package com.deark.be.alarm.repository;

import com.deark.be.alarm.dto.response.AlarmResponse;
import com.deark.be.alarm.dto.response.AlarmResponseList;
import com.deark.be.order.domain.type.Status;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.deark.be.alarm.domain.QAlarm.alarm;

@Repository
@RequiredArgsConstructor
public class AlarmRepositoryImpl implements AlarmRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public AlarmResponseList findAllByUserIdAndType(Long userId, Status status) {
        List<AlarmResponse> alarms = jpaQueryFactory
                .select(Projections.constructor(
                        AlarmResponse.class,
                        alarm.id,
                        selectDesignImageUrl(),
                        alarm.message.store.name,
                        alarm.message.status,
                        alarm.message.responseTime,
                        alarm.message.id,
                        alarm.isRead
                ))
                .from(alarm)
                .join(alarm.message)
                .where(
                        alarm.user.id.eq(userId),
                        buildStatusCondition(status)
                )
                .orderBy(alarm.id.desc())
                .fetch();

        Long count = jpaQueryFactory
                .select(alarm.count())
                .from(alarm)
                .join(alarm.message)
                .where(
                        alarm.user.id.eq(userId),
                        buildStatusCondition(status)
                )
                .fetchOne();

        return AlarmResponseList.builder()
                .alarmCount(count != null ? count : 0)
                .responseList(alarms)
                .build();
    }

    private Expression<String> selectDesignImageUrl() {
        return new CaseBuilder()
                .when(alarm.message.designUrl.isNotNull())
                .then(alarm.message.designUrl)
                .otherwise(alarm.message.design.imageUrl);
    }

    private BooleanExpression buildStatusCondition(Status status) {
        if (ObjectUtils.isEmpty(status)) {
            return alarm.message.status.in(Status.ACCEPTED, Status.REJECTED);
        } else {
            return alarm.message.status.eq(status);
        }
    }
}
