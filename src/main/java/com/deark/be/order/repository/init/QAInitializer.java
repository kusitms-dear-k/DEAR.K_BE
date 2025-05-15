package com.deark.be.order.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(5)
@DummyDataInit
public class QAInitializer implements ApplicationRunner {

    private final QARepository qaRepository;
    private final MessageRepository messageRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (qaRepository.count() > 0) {
            log.info("[QA] 더미 데이터 존재");
        } else {
            Message MESSAGE1 = messageRepository.findById(1L).orElseThrow();
            Message MESSAGE2 = messageRepository.findById(2L).orElseThrow();
            Message MESSAGE4 = messageRepository.findById(4L).orElseThrow();
            Message MESSAGE7 = messageRepository.findById(7L).orElseThrow();

            QA DUMMY_QA1 = QA.builder()
                    .message(MESSAGE1)
                    .question("name")
                    .answer("김혜연")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA2 = QA.builder()
                    .message(MESSAGE1)
                    .question("phoneNumber")
                    .answer("010-4037-2419")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA3 = QA.builder()
                    .message(MESSAGE1)
                    .question("pickupDate")
                    .answer("2025년 5월 30일 금요일")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA4 = QA.builder()
                    .message(MESSAGE1)
                    .question("pickupTime")
                    .answer("14시 30분")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA6 = QA.builder()
                    .message(MESSAGE1)
                    .question("additionalRequest")
                    .answer("문구를 생일축하해! 로 넣어주세요. 사진 속 리본 3개를 곰돌이 위에 올려주세요.")
                    .isRequired(false)
                    .build();

            QA DUMMY_QA7 = QA.builder()
                    .message(MESSAGE1)
                    .question("size")
                    .answer("도시락 케이크")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA8 = QA.builder()
                    .message(MESSAGE1)
                    .question("cream")
                    .answer("블루베리")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA9 = QA.builder()
                    .message(MESSAGE1)
                    .question("sheet")
                    .answer("바닐라")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA10 = QA.builder()
                    .message(MESSAGE2)
                    .question("name")
                    .answer("박소윤")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA11 = QA.builder()
                    .message(MESSAGE2)
                    .question("phoneNumber")
                    .answer("010-1234-5678")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA12 = QA.builder()
                    .message(MESSAGE2)
                    .question("pickupDate")
                    .answer("2025년 6월 1일 일요일")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA13 = QA.builder()
                    .message(MESSAGE2)
                    .question("pickupTime")
                    .answer("15시 00분")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA15 = QA.builder()
                    .message(MESSAGE2)
                    .question("additionalRequest")
                    .answer("문구를 생일축하해! 로 넣어주세요. 사진 속 리본 3개를 곰돌이 위에 올려주세요.")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA16 = QA.builder()
                    .message(MESSAGE2)
                    .question("size")
                    .answer("도시락 케이크")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA17 = QA.builder()
                    .message(MESSAGE2)
                    .question("cream")
                    .answer("생크림")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA18 = QA.builder()
                    .message(MESSAGE2)
                    .question("sheet")
                    .answer("초코")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA19 = QA.builder()
                    .message(MESSAGE4)
                    .question("name")
                    .answer("이지은")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA20 = QA.builder()
                    .message(MESSAGE4)
                    .question("phoneNumber")
                    .answer("010-9876-5432")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA21 = QA.builder()
                    .message(MESSAGE4)
                    .question("pickupDate")
                    .answer("2025년 6월 17일 화요일")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA22 = QA.builder()
                    .message(MESSAGE4)
                    .question("pickupTime")
                    .answer("16시 00분")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA24 = QA.builder()
                    .message(MESSAGE4)
                    .question("additionalRequest")
                    .answer("곰돌이 눈을 더 크게 해주세요. 초코시트로 해주세요.")
                    .isRequired(false)
                    .build();

            QA DUMMY_QA25 = QA.builder()
                    .message(MESSAGE4)
                    .question("size")
                    .answer("도시락 케이크")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA26 = QA.builder()
                    .message(MESSAGE4)
                    .question("cream")
                    .answer("딸기")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA27 = QA.builder()
                    .message(MESSAGE4)
                    .question("sheet")
                    .answer("초코")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA28 = QA.builder()
                    .message(MESSAGE7)
                    .question("name")
                    .answer("최유진")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA29 = QA.builder()
                    .message(MESSAGE7)
                    .question("phoneNumber")
                    .answer("010-5555-6666")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA30 = QA.builder()
                    .message(MESSAGE7)
                    .question("pickupDate")
                    .answer("2025년 6월 20일 금요일")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA31 = QA.builder()
                    .message(MESSAGE7)
                    .question("pickupTime")
                    .answer("13시 30분")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA33 = QA.builder()
                    .message(MESSAGE7)
                    .question("additionalRequest")
                    .answer("하트 모양을 더 선명하게 해주세요. 핑크색 크림으로 해주세요.")
                    .isRequired(false)
                    .build();

            QA DUMMY_QA34 = QA.builder()
                    .message(MESSAGE7)
                    .question("size")
                    .answer("1호 케이크")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA35 = QA.builder()
                    .message(MESSAGE7)
                    .question("cream")
                    .answer("바닐라")
                    .isRequired(true)
                    .build();

            QA DUMMY_QA36 = QA.builder()
                    .message(MESSAGE7)
                    .question("sheet")
                    .answer("바닐라")
                    .isRequired(true)
                    .build();

            List<QA> qaList = new ArrayList<>(List.of(
                    DUMMY_QA1, DUMMY_QA2, DUMMY_QA3, DUMMY_QA4, DUMMY_QA6, DUMMY_QA7, DUMMY_QA8, DUMMY_QA9,
                    DUMMY_QA10, DUMMY_QA11, DUMMY_QA12, DUMMY_QA13, DUMMY_QA15, DUMMY_QA16, DUMMY_QA17, DUMMY_QA18,
                    DUMMY_QA19, DUMMY_QA20, DUMMY_QA21, DUMMY_QA22, DUMMY_QA24, DUMMY_QA25, DUMMY_QA26, DUMMY_QA27,
                    DUMMY_QA28, DUMMY_QA29, DUMMY_QA30, DUMMY_QA31, DUMMY_QA33, DUMMY_QA34, DUMMY_QA35, DUMMY_QA36
            ));

            qaRepository.saveAll(qaList);
        }
    }
}
