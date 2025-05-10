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

            commonQuestionList.add(COMMON_QUESTION1);

            commonQuestionRepository.saveAll(commonQuestionList);
        }
    }
}
