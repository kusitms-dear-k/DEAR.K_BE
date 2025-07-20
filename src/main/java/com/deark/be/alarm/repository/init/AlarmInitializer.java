package com.deark.be.alarm.repository.init;

import com.deark.be.alarm.domain.Alarm;
import com.deark.be.alarm.repository.AlarmRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

import static com.deark.be.alarm.domain.type.Type.ORDER;

@Slf4j
@RequiredArgsConstructor
@Order(6)
@DummyDataInit
public class AlarmInitializer implements ApplicationRunner {

    private final AlarmRepository alarmRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (alarmRepository.count() > 0) {
            log.info("[Alarm] 더미 데이터 존재");
        } else {
            User USER1 = userRepository.findById(1L).orElseThrow();

            OrderRequestForm ORDER_REQUEST_FORM2 = messageRepository.findById(2L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM3 = messageRepository.findById(3L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM5 = messageRepository.findById(5L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM6 = messageRepository.findById(6L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM8 = messageRepository.findById(8L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM9 = messageRepository.findById(9L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM10 = messageRepository.findById(10L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM11 = messageRepository.findById(11L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM12 = messageRepository.findById(12L).orElseThrow();
            OrderRequestForm ORDER_REQUEST_FORM13 = messageRepository.findById(13L).orElseThrow();

            List<Alarm> alarmList = new ArrayList<>();

            Alarm DUMMY_ALARM1 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM2)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM2 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM3)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM3 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM5)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM4 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM6)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM5 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM8)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM6 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM9)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM7 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM10)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM8 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM11)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM9 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM12)
                    .content("주문 요청이 접수되었습니다.")
                    .type(ORDER)
                    .isRead(false)
                    .isDeleted(false)
                    .build();

            Alarm DUMMY_ALARM10 = Alarm.builder()
                    .user(USER1)
                    .orderRequestForm(ORDER_REQUEST_FORM13)
                    .content("주문 요청이 접수되었습니다.")
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
            alarmList.add(DUMMY_ALARM8);
            alarmList.add(DUMMY_ALARM9);
            alarmList.add(DUMMY_ALARM10);

            alarmRepository.saveAll(alarmList);
        }
    }
}