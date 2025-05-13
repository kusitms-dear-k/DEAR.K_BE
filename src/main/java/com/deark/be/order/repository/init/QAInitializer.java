package com.deark.be.order.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.cloud.aws.s3.url}/design")
    private String designImageUrl;

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

            QA DUMMY_QA19 = QA.builder()
                    .message(MESSAGE4)
                    .question("이름")
                    .answer("이지은")
                    .build();

            QA DUMMY_QA20 = QA.builder()
                    .message(MESSAGE4)
                    .question("전화번호")
                    .answer("010-9876-5432")
                    .build();

            QA DUMMY_QA21 = QA.builder()
                    .message(MESSAGE4)
                    .question("픽업 희망 일자")
                    .answer("2025년 6월 15일 일요일")
                    .build();

            QA DUMMY_QA22 = QA.builder()
                    .message(MESSAGE4)
                    .question("픽업 희망 시간")
                    .answer("16시 00분")
                    .build();

            QA DUMMY_QA24 = QA.builder()
                    .message(MESSAGE4)
                    .question("추가 요청사항")
                    .answer("곰돌이 눈을 더 크게 해주세요. 초코시트로 해주세요.")
                    .build();

            QA DUMMY_QA25 = QA.builder()
                    .message(MESSAGE4)
                    .question("크기")
                    .answer("도시락 케이크")
                    .build();

            QA DUMMY_QA26 = QA.builder()
                    .message(MESSAGE4)
                    .question("크림 맛")
                    .answer("딸기")
                    .build();

            QA DUMMY_QA27 = QA.builder()
                    .message(MESSAGE4)
                    .question("시트 맛")
                    .answer("초코")
                    .build();

            QA DUMMY_QA28 = QA.builder()
                    .message(MESSAGE7)
                    .question("이름")
                    .answer("최유진")
                    .build();

            QA DUMMY_QA29 = QA.builder()
                    .message(MESSAGE7)
                    .question("전화번호")
                    .answer("010-5555-6666")
                    .build();

            QA DUMMY_QA30 = QA.builder()
                    .message(MESSAGE7)
                    .question("픽업 희망 일자")
                    .answer("2025년 6월 20일 금요일")
                    .build();

            QA DUMMY_QA31 = QA.builder()
                    .message(MESSAGE7)
                    .question("픽업 희망 시간")
                    .answer("13시 30분")
                    .build();

            QA DUMMY_QA32 = QA.builder()
                    .message(MESSAGE7)
                    .question("디자인")
                    .answer(designImageUrl + "/bear.png")
                    .build();

            QA DUMMY_QA33 = QA.builder()
                    .message(MESSAGE7)
                    .question("추가 요청사항")
                    .answer("하트 모양을 더 선명하게 해주세요. 핑크색 크림으로 해주세요.")
                    .build();

            QA DUMMY_QA34 = QA.builder()
                    .message(MESSAGE7)
                    .question("크기")
                    .answer("1호 케이크")
                    .build();

            QA DUMMY_QA35 = QA.builder()
                    .message(MESSAGE7)
                    .question("크림 맛")
                    .answer("바닐라")
                    .build();

            QA DUMMY_QA36 = QA.builder()
                    .message(MESSAGE7)
                    .question("시트 맛")
                    .answer("바닐라")
                    .build();

            List<QA> qaList = new ArrayList<>(List.of(
                    DUMMY_QA1, DUMMY_QA2, DUMMY_QA3, DUMMY_QA4, DUMMY_QA6, DUMMY_QA7, DUMMY_QA8, DUMMY_QA9,
                    DUMMY_QA10, DUMMY_QA11, DUMMY_QA12, DUMMY_QA13, DUMMY_QA14, DUMMY_QA15, DUMMY_QA16, DUMMY_QA17, DUMMY_QA18,
                    DUMMY_QA19, DUMMY_QA20, DUMMY_QA21, DUMMY_QA22, DUMMY_QA24, DUMMY_QA25, DUMMY_QA26, DUMMY_QA27,
                    DUMMY_QA28, DUMMY_QA29, DUMMY_QA30, DUMMY_QA31, DUMMY_QA32, DUMMY_QA33, DUMMY_QA34, DUMMY_QA35, DUMMY_QA36
            ));

            qaRepository.saveAll(qaList);
        }
    }
}
