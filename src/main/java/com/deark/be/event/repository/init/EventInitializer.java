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
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(4)
@DummyDataInit
public class EventInitializer implements ApplicationRunner {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (eventRepository.count() > 0) {
            log.info("[Event] 더미 데이터 존재");
            return;
        }

        User user = userRepository.findById(1L).orElseThrow(); // DUMMY_USER1

        Event DUMMY_EVENT0 = Event.builder()
                .user(user)
                .title("남자친구 생일")
                .address("서울특별시 송파구 백제고분로 123")
                .eventDate(LocalDate.now().plusDays(3))
                .build();

        Event DUMMY_EVENT1 = Event.builder()
                .user(user)
                .title("친구 결혼 축하")
                .address("서울특별시 강남구 논현로 508")
                .eventDate(LocalDate.now().plusWeeks(2))
                .build();

        Event DUMMY_EVENT2 = Event.builder()
                .user(user)
                .title("전역 축하")
                .address("서울특별시 중랑구 망우로 300")
                .eventDate(LocalDate.now().plusMonths(1))
                .build();

        eventRepository.saveAll(List.of(DUMMY_EVENT0, DUMMY_EVENT1, DUMMY_EVENT2));

        log.info("[Event] DUMMY_USER1용 이벤트 3개 생성 완료");
    }
}