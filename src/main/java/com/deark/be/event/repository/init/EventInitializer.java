package com.deark.be.event.repository.init;

import com.deark.be.event.domain.Event;
import com.deark.be.event.repository.EventRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class EventInitializer implements ApplicationRunner {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (eventRepository.count() > 0) {
            log.info("[Event] 더미 데이터 존재");
        } else {
            User USER1 = userRepository.findById(1L).orElseThrow();

            List<Event> eventList = new ArrayList<>();

            Event DUMMY_EVENT0 = Event.builder()
                    .user(USER1)
                    .title("남자친구 생일")
                    .address("서울특별시 송파구 백제고분로 123")
                    .eventDate(LocalDate.now().plusDays(3))
                    .build();

            Event DUMMY_EVENT1 = Event.builder()
                    .user(USER1)
                    .title("친구 결혼 축하")
                    .address("서울특별시 강남구 논현로 508")
                    .eventDate(LocalDate.now().plusWeeks(2))
                    .build();

            Event DUMMY_EVENT2 = Event.builder()
                    .user(USER1)
                    .title("전역 축하")
                    .address("서울특별시 중랑구 망우로 300")
                    .eventDate(LocalDate.now().plusMonths(1))
                    .build();

            Event BIRTHDAY_PARTY = Event.builder()
                    .user(USER1)
                    .title("아이 생일 파티")
                    .address("서울시 강남구 테헤란로 123")
                    .eventDate(LocalDate.now().plusDays(7))
                    .build();

            Event WEDDING = Event.builder()
                    .user(USER1)
                    .title("결혼식")
                    .address("서울시 서초구 서초대로 456")
                    .eventDate(LocalDate.now().plusDays(14))
                    .build();

            Event CORPORATE_EVENT = Event.builder()
                    .user(USER1)
                    .title("기업 창립 기념일")
                    .address("서울시 송파구 올림픽로 789")
                    .eventDate(LocalDate.now().plusDays(21))
                    .build();

            eventList.add(DUMMY_EVENT0);
            eventList.add(DUMMY_EVENT1);
            eventList.add(DUMMY_EVENT2);
            eventList.add(BIRTHDAY_PARTY);
            eventList.add(WEDDING);
            eventList.add(CORPORATE_EVENT);

            eventRepository.saveAll(eventList);
        }
    }
} 