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
@Order(4)
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

            List<QA> qaList = new ArrayList<>();

            QA DUMMY_QA1 = QA.builder()
                    .message(MESSAGE1)
                    .question("이름")
                    .answer("김혜연")
                    .build();

            QA DUMMY_QA2 = QA.builder()
                    .message(MESSAGE1)
                    .question("전화번호")
                    .answer("010-4037-2419")
                    .build();

            QA DUMMY_QA3 = QA.builder()
                    .message(MESSAGE1)
                    .question("픽업 희망 일자")
                    .answer("2025년 5월 31일 토요일")
                    .build();

            QA DUMMY_QA4 = QA.builder()
                    .message(MESSAGE1)
                    .question("픽업 희망 시간")
                    .answer("14시 30분")
                    .build();

            QA DUMMY_QA5 = QA.builder()
                    .message(MESSAGE1)
                    .question("디자인")
                    .answer("레인보우케이크")
                    .build();

            QA DUMMY_QA6 = QA.builder()
                    .message(MESSAGE1)
                    .question("추가 요청사항")
                    .answer("문구를 생일축하해! 로 넣어주세요. 사진 속 리본 3개를 곰돌이 위에 올려주세요.")
                    .build();

            QA DUMMY_QA7 = QA.builder()
                    .message(MESSAGE1)
                    .question("크기")
                    .answer("도시락 케이크")
                    .build();

            QA DUMMY_QA8 = QA.builder()
                    .message(MESSAGE1)
                    .question("크림 맛")
                    .answer("블루베리")
                    .build();

            QA DUMMY_QA9 = QA.builder()
                    .message(MESSAGE1)
                    .question("시트 맛")
                    .answer("바닐라")
                    .build();

            QA DUMMY_QA10 = QA.builder()
                    .message(MESSAGE2)
                    .question("이름")
                    .answer("박소윤")
                    .build();

            QA DUMMY_QA11 = QA.builder()
                    .message(MESSAGE2)
                    .question("전화번호")
                    .answer("010-1234-5678")
                    .build();

            QA DUMMY_QA12 = QA.builder()
                    .message(MESSAGE2)
                    .question("픽업 희망 일자")
                    .answer("2025년 6월 1일 일요일")
                    .build();

            QA DUMMY_QA13 = QA.builder()
                    .message(MESSAGE2)
                    .question("픽업 희망 시간")
                    .answer("15시 00분")
                    .build();

            QA DUMMY_QA14 = QA.builder()
                    .message(MESSAGE2)
                    .question("디자인")
                    .answer("생일케이크")
                    .build();

            QA DUMMY_QA15 = QA.builder()
                    .message(MESSAGE2)
                    .question("추가 요청사항")
                    .answer("문구를 생일축하해! 로 넣어주세요. 사진 속 리본 3개를 곰돌이 위에 올려주세요.")
                    .build();

            QA DUMMY_QA16 = QA.builder()
                    .message(MESSAGE2)
                    .question("크기")
                    .answer("도시락 케이크")
                    .build();

            QA DUMMY_QA17 = QA.builder()
                    .message(MESSAGE2)
                    .question("크림 맛")
                    .answer("생크림")
                    .build();

            QA DUMMY_QA18 = QA.builder()
                    .message(MESSAGE2)
                    .question("시트 맛")
                    .answer("초코")
                    .build();

            qaList.add(DUMMY_QA1);
            qaList.add(DUMMY_QA2);
            qaList.add(DUMMY_QA3);
            qaList.add(DUMMY_QA4);
            qaList.add(DUMMY_QA5);
            qaList.add(DUMMY_QA6);
            qaList.add(DUMMY_QA7);
            qaList.add(DUMMY_QA8);
            qaList.add(DUMMY_QA9);
            qaList.add(DUMMY_QA10);
            qaList.add(DUMMY_QA11);
            qaList.add(DUMMY_QA12);
            qaList.add(DUMMY_QA13);
            qaList.add(DUMMY_QA14);
            qaList.add(DUMMY_QA15);
            qaList.add(DUMMY_QA16);
            qaList.add(DUMMY_QA17);
            qaList.add(DUMMY_QA18);

            qaRepository.saveAll(qaList);
        }
    }
}
