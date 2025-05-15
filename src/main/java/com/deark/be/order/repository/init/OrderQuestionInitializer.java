package com.deark.be.order.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.CommonQuestion;
import com.deark.be.order.domain.OrderQuestion;
import com.deark.be.order.repository.CommonQuestionRepository;
import com.deark.be.order.repository.OrderQuestionRepository;
import com.deark.be.store.domain.Store;
import com.deark.be.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

import static com.deark.be.order.domain.type.QuestionType.COMMON;
import static com.deark.be.order.domain.type.QuestionType.CUSTOM;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class OrderQuestionInitializer implements ApplicationRunner {

    private final OrderQuestionRepository orderQuestionRepository;
    private final CommonQuestionRepository commonQuestionRepository;
    private final StoreRepository storeRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (orderQuestionRepository.count() > 0) {
            log.info("[Order Question] 더미 데이터 존재");
        } else {
            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Store STORE2 = storeRepository.findById(2L).orElseThrow();

            CommonQuestion COMMON_QUESTION1 = commonQuestionRepository.findById(1L).orElseThrow();
            CommonQuestion COMMON_QUESTION2 = commonQuestionRepository.findById(2L).orElseThrow();
            CommonQuestion COMMON_QUESTION3 = commonQuestionRepository.findById(3L).orElseThrow();
            CommonQuestion COMMON_QUESTION4 = commonQuestionRepository.findById(4L).orElseThrow();
            CommonQuestion COMMON_QUESTION5 = commonQuestionRepository.findById(5L).orElseThrow();
            CommonQuestion COMMON_QUESTION6 = commonQuestionRepository.findById(6L).orElseThrow();
            CommonQuestion COMMON_QUESTION7 = commonQuestionRepository.findById(7L).orElseThrow();
            CommonQuestion COMMON_QUESTION8 = commonQuestionRepository.findById(8L).orElseThrow();

            List<OrderQuestion> orderQuestionList = new ArrayList<>();

            OrderQuestion ORDER_QUESTION1 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION1)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION2 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION2)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION3 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION3)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION4 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION4)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION5 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION5)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION6 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION6)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION7 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION7)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION8 = OrderQuestion.builder()
                    .store(STORE1)
                    .commonQuestion(COMMON_QUESTION8)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION9 = OrderQuestion.builder()
                    .store(STORE1)
                    .questionType(CUSTOM)
                    .title("parking")
                    .content("주차 여부를 알려주세요.")
                    .isRequired(false)
                    .build();

            OrderQuestion ORDER_QUESTION10 = OrderQuestion.builder()
                    .store(STORE1)
                    .questionType(CUSTOM)
                    .title("additionalRequest")
                    .content("원하는 요청사항을 작성해주세요.")
                    .isRequired(false)
                    .build();

            OrderQuestion ORDER_QUESTION11 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION1)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION12 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION2)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION13 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION3)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION14 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION4)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION15 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION5)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION16 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION6)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION17 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION7)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION18 = OrderQuestion.builder()
                    .store(STORE2)
                    .commonQuestion(COMMON_QUESTION8)
                    .questionType(COMMON)
                    .build();

            OrderQuestion ORDER_QUESTION19 = OrderQuestion.builder()
                    .store(STORE2)
                    .questionType(CUSTOM)
                    .title("additionalRequest")
                    .content("원하는 요청사항을 작성해주세요.")
                    .isRequired(false)
                    .build();

            orderQuestionList.add(ORDER_QUESTION1);
            orderQuestionList.add(ORDER_QUESTION2);
            orderQuestionList.add(ORDER_QUESTION3);
            orderQuestionList.add(ORDER_QUESTION4);
            orderQuestionList.add(ORDER_QUESTION5);
            orderQuestionList.add(ORDER_QUESTION6);
            orderQuestionList.add(ORDER_QUESTION7);
            orderQuestionList.add(ORDER_QUESTION8);
            orderQuestionList.add(ORDER_QUESTION9);
            orderQuestionList.add(ORDER_QUESTION10);
            orderQuestionList.add(ORDER_QUESTION11);
            orderQuestionList.add(ORDER_QUESTION12);
            orderQuestionList.add(ORDER_QUESTION13);
            orderQuestionList.add(ORDER_QUESTION14);
            orderQuestionList.add(ORDER_QUESTION15);
            orderQuestionList.add(ORDER_QUESTION16);
            orderQuestionList.add(ORDER_QUESTION17);
            orderQuestionList.add(ORDER_QUESTION18);
            orderQuestionList.add(ORDER_QUESTION19);

            orderQuestionRepository.saveAll(orderQuestionList);
        }
    }
}
