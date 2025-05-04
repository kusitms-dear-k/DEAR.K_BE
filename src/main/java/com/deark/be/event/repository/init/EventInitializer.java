package com.deark.be.event.repository.init;

import com.deark.be.event.domain.Event;
import com.deark.be.event.repository.EventRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@DummyDataInit
public class EventInitializer implements ApplicationRunner {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (eventRepository.count() > 0) {
            log.info("[Event] 더미 데이터 존재");
        } else {
            User DUMMY_USER = userRepository.findById(1L).orElseThrow();

            List<Event> eventList = new ArrayList<>();

            Event BIRTHDAY_PARTY = Event.builder()
                    .user(DUMMY_USER)
                    .title("아이 생일 파티")
                    .address("서울시 강남구 테헤란로 123")
                    .eventDate(LocalDateTime.now().plusDays(7))
                    .build();

            Event WEDDING = Event.builder()
                    .user(DUMMY_USER)
                    .title("결혼식")
                    .address("서울시 서초구 서초대로 456")
                    .eventDate(LocalDateTime.now().plusDays(14))
                    .build();

            Event CORPORATE_EVENT = Event.builder()
                    .user(DUMMY_USER)
                    .title("기업 창립 기념일")
                    .address("서울시 송파구 올림픽로 789")
                    .eventDate(LocalDateTime.now().plusDays(21))
                    .build();

            eventList.add(BIRTHDAY_PARTY);
            eventList.add(WEDDING);
            eventList.add(CORPORATE_EVENT);

            eventRepository.saveAll(eventList);
        }
    }
} 