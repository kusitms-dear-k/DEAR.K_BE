package com.deark.be.event.repository;

import com.deark.be.event.domain.Event;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.deark.be.event.domain.QEvent.event;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Event> findFirstUpcomingEvent(Long userId) {
        Event result =queryFactory
                .selectFrom(event)
                .where(
                        event.user.id.eq(userId),
                        event.eventDate.goe(LocalDate.now())
                )
                .orderBy(event.eventDate.asc())
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
