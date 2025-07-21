package com.deark.be.order.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.OrderRequestFormQa;
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
@Order(6)
@DummyDataInit
public class QAInitializer implements ApplicationRunner {

    private final QARepository qaRepository;
    private final MessageRepository messageRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (qaRepository.count() > 0) {
            log.info("[QA] 더미 데이터 존재");
        } else {
            OrderRequestForm OrderRequestForm1 = messageRepository.findById(1L).orElseThrow();
            OrderRequestForm OrderRequestForm2 = messageRepository.findById(2L).orElseThrow();
            OrderRequestForm OrderRequestForm4 = messageRepository.findById(4L).orElseThrow();
            OrderRequestForm OrderRequestForm5 = messageRepository.findById(5L).orElseThrow();
            OrderRequestForm OrderRequestForm7 = messageRepository.findById(7L).orElseThrow();

            OrderRequestFormQa DUMMY_OrderRequestFormQa1 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("이름")
                    .answer("김혜연")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa2 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("전화번호")
                    .answer("010-4037-2419")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa3 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("픽업 희망 일자")
                    .answer("2025년 5월 30일 금요일")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa4 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("픽업 희망 시간")
                    .answer("14시 30분")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa5 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("기타 요청사항")
                    .answer("주차증 발급해주세요.")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa6 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("추가 요청사항")
                    .answer("문구를 생일축하해! 로 넣어주세요. 사진 속 리본 3개를 곰돌이 위에 올려주세요.")
                    .isRequired(false)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa7 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("크기")
                    .answer("도시락 케이크")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa8 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("크림 맛")
                    .answer("블루베리 크림")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa9 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm1)
                    .question("시트 맛")
                    .answer("바닐라 시트")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa10 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("이름")
                    .answer("박소윤")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa11 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("전화번호")
                    .answer("010-1234-5678")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa12 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("픽업 희망 일자")
                    .answer("2025년 6월 1일 일요일")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa13 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("픽업 희망 시간")
                    .answer("15시 00분")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa14 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("기타 요청사항")
                    .answer("없습니다.")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa15 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("추가 요청사항")
                    .answer("문구를 생일축하해! 로 넣어주세요. 사진 속 리본 3개를 곰돌이 위에 올려주세요.")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa16 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("크기")
                    .answer("도시락 케이크")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa17 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("크림 맛")
                    .answer("생크림")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa18 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm2)
                    .question("시트 맛")
                    .answer("초코")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa19 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("이름")
                    .answer("이지은")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa20 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("전화번호")
                    .answer("010-9876-5432")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa21 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("픽업 희망 일자")
                    .answer("2025년 6월 17일 화요일")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa22 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("픽업 희망 시간")
                    .answer("16시 00분")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa23 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("기타 요청사항")
                    .answer("보냉백 추가할게요!")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa24 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("추가 요청사항")
                    .answer("곰돌이 눈을 더 크게 해주세요. 초코시트로 해주세요.")
                    .isRequired(false)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa25 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("크기")
                    .answer("도시락 케이크")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa26 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("크림 맛")
                    .answer("딸기")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa27 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm4)
                    .question("시트 맛")
                    .answer("초코")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa28 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("이름")
                    .answer("최유진")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa29 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("전화번호")
                    .answer("010-5555-6666")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa30 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("픽업 희망 일자")
                    .answer("2025년 6월 20일 금요일")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa31 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("픽업 희망 시간")
                    .answer("13시 30분")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa32 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("기타 요청사항")
                    .answer("없습니다.")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa33 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("추가 요청사항")
                    .answer("하트 모양을 더 선명하게 해주세요. 핑크색 크림으로 해주세요.")
                    .isRequired(false)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa34 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("크기")
                    .answer("1호 케이크")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa35 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("크림 맛")
                    .answer("바닐라")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa36 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm7)
                    .question("시트 맛")
                    .answer("바닐라")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa37 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("이름")
                    .answer("정아린")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa38 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("전화번호")
                    .answer("010-5643-4254")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa39 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("픽업 희망 일자")
                    .answer("2025년 6월 20일 금요일")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa40 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("픽업 희망 시간")
                    .answer("21시 00분")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa41 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("기타 요청사항")
                    .answer("없습니다.")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa42 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("추가 요청사항")
                    .answer("배경색은 보라색으로 해주세요")
                    .isRequired(false)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa43 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("크기")
                    .answer("2호")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa44 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("크림 맛")
                    .answer("바닐라")
                    .isRequired(true)
                    .build();

            OrderRequestFormQa DUMMY_OrderRequestFormQa45 = OrderRequestFormQa.builder()
                    .orderRequestForm(OrderRequestForm5)
                    .question("시트 맛")
                    .answer("초콜릿")
                    .isRequired(true)
                    .build();

            List<OrderRequestFormQa> orderRequestFormQaList = new ArrayList<>(List.of(
                    DUMMY_OrderRequestFormQa1, DUMMY_OrderRequestFormQa2, DUMMY_OrderRequestFormQa3, DUMMY_OrderRequestFormQa4, DUMMY_OrderRequestFormQa6, DUMMY_OrderRequestFormQa7, DUMMY_OrderRequestFormQa8, DUMMY_OrderRequestFormQa9,
                    DUMMY_OrderRequestFormQa10, DUMMY_OrderRequestFormQa11, DUMMY_OrderRequestFormQa12, DUMMY_OrderRequestFormQa13, DUMMY_OrderRequestFormQa15, DUMMY_OrderRequestFormQa16, DUMMY_OrderRequestFormQa17, DUMMY_OrderRequestFormQa18,
                    DUMMY_OrderRequestFormQa19, DUMMY_OrderRequestFormQa20, DUMMY_OrderRequestFormQa21, DUMMY_OrderRequestFormQa22, DUMMY_OrderRequestFormQa24, DUMMY_OrderRequestFormQa25, DUMMY_OrderRequestFormQa26, DUMMY_OrderRequestFormQa27,
                    DUMMY_OrderRequestFormQa28, DUMMY_OrderRequestFormQa29, DUMMY_OrderRequestFormQa30, DUMMY_OrderRequestFormQa31, DUMMY_OrderRequestFormQa33, DUMMY_OrderRequestFormQa34, DUMMY_OrderRequestFormQa35, DUMMY_OrderRequestFormQa36,
                    DUMMY_OrderRequestFormQa5, DUMMY_OrderRequestFormQa14, DUMMY_OrderRequestFormQa23, DUMMY_OrderRequestFormQa32, DUMMY_OrderRequestFormQa37, DUMMY_OrderRequestFormQa38, DUMMY_OrderRequestFormQa39, DUMMY_OrderRequestFormQa40, DUMMY_OrderRequestFormQa41,
                    DUMMY_OrderRequestFormQa42, DUMMY_OrderRequestFormQa43, DUMMY_OrderRequestFormQa44, DUMMY_OrderRequestFormQa45
            ));

            qaRepository.saveAll(orderRequestFormQaList);
        }
    }
}