package com.deark.be.alarm.repository.init;

import com.deark.be.alarm.domain.Alarm;
import com.deark.be.alarm.repository.AlarmRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.Message;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

import static com.deark.be.alarm.domain.type.Type.ORDER;

@Slf4j
@RequiredArgsConstructor
@Order(5)
@DummyDataInit
public class AlarmInitializer implements ApplicationRunner {

    private final AlarmRepository alarmRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Value("${spring.cloud.aws.s3.url}/design")
    private String designImageUrl;

    @Override
    public void run(ApplicationArguments args) {
        if (alarmRepository.count() > 0) {
            log.info("[Alarm] 더미 데이터 존재");
        } else {
            User USER1 = userRepository.findById(1L).orElseThrow();

            Message MESSAGE2 = messageRepository.findById(2L).orElseThrow();
            Message MESSAGE3 = messageRepository.findById(3L).orElseThrow();
            Message MESSAGE5 = messageRepository.findById(5L).orElseThrow();
            Message MESSAGE6 = messageRepository.findById(6L).orElseThrow();
            Message MESSAGE8 = messageRepository.findById(8L).orElseThrow();
            Message MESSAGE9 = messageRepository.findById(9L).orElseThrow();
            Message MESSAGE10 = messageRepository.findById(10L).orElseThrow();

            List<Alarm> alarmList = new ArrayList<>();

            Alarm DUMMY_ALARM1 = Alarm.builder()
                    .user(USER1)
                    .message(MESSAGE2)
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM2 = Alarm.builder()
                    .user(USER1)
                    .message(MESSAGE3)
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM3 = Alarm.builder()
                    .user(USER1)
                    .message(MESSAGE5)
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM4 = Alarm.builder()
                    .user(USER1)
                    .message(MESSAGE6)
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM5 = Alarm.builder()
                    .user(USER1)
                    .message(MESSAGE8)
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM6 = Alarm.builder()
                    .user(USER1)
                    .message(MESSAGE9)
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM7 = Alarm.builder()
                    .user(USER1)
                    .message(MESSAGE10)
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            alarmList.add(DUMMY_ALARM1);
            alarmList.add(DUMMY_ALARM2);
            alarmList.add(DUMMY_ALARM3);
            alarmList.add(DUMMY_ALARM4);
            alarmList.add(DUMMY_ALARM5);
            alarmList.add(DUMMY_ALARM6);
            alarmList.add(DUMMY_ALARM7);

            alarmRepository.saveAll(alarmList);
        }
    }
}