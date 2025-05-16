package com.deark.be.order.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.CommonQuestion;
import com.deark.be.order.repository.CommonQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class CommonQuestionInitializer implements ApplicationRunner {

    private final CommonQuestionRepository commonQuestionRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (commonQuestionRepository.count() > 0) {
            log.info("[Common Question] 더미 데이터 존재");
        } else {
            List<CommonQuestion> commonQuestionList = new ArrayList<>();

            CommonQuestion COMMON_QUESTION1 = CommonQuestion.builder()
                    .title("이름")
                    .hint("이름을 입력해주세요.")
                    .isRequired(true)
                    .build();

            CommonQuestion COMMON_QUESTION2 = CommonQuestion.builder()
                    .title("전화번호")
                    .hint("전화번호를 입력해주세요.")
                    .isRequired(true)
                    .build();

            CommonQuestion COMMON_QUESTION3 = CommonQuestion.builder()
                    .title("픽업 희망 일자")
                    .hint("픽업 일자 및 시간을 선택해주세요.")
                    .isRequired(true)
                    .build();

            CommonQuestion COMMON_QUESTION4 = CommonQuestion.builder()
                    .title("크기")
                    .hint("케이크 크기를 선택해주세요.")
                    .isRequired(true)
                    .build();

            CommonQuestion COMMON_QUESTION5 = CommonQuestion.builder()
                    .title("크림")
                    .hint("크림 맛을 선택해주세요.")
                    .isRequired(true)
                    .build();

            CommonQuestion COMMON_QUESTION6 = CommonQuestion.builder()
                    .title("시트")
                    .hint("시트 맛을 선택해주세요.")
                    .isRequired(true)
                    .build();

            CommonQuestion COMMON_QUESTION7 = CommonQuestion.builder()
                    .title("디자인")
                    .hint("원하는 케이크 디자인을 보여주세요.")
                    .isRequired(false)
                    .build();

            CommonQuestion COMMON_QUESTION8 = CommonQuestion.builder()
                    .title("요청사항")
                    .hint("텍스트 요청사항을 설명해주세요.")
                    .isRequired(false)
                    .build();

            commonQuestionList.add(COMMON_QUESTION1);
            commonQuestionList.add(COMMON_QUESTION2);
            commonQuestionList.add(COMMON_QUESTION3);
            commonQuestionList.add(COMMON_QUESTION4);
            commonQuestionList.add(COMMON_QUESTION5);
            commonQuestionList.add(COMMON_QUESTION6);
            commonQuestionList.add(COMMON_QUESTION7);
            commonQuestionList.add(COMMON_QUESTION8);

            commonQuestionRepository.saveAll(commonQuestionList);
        }
    }
}
