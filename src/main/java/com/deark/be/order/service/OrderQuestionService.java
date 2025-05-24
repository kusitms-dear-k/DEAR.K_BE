package com.deark.be.order.service;

import com.deark.be.order.domain.OrderQuestion;
import com.deark.be.order.dto.response.OrderQuestionResponse;
import com.deark.be.order.dto.response.OrderQuestionResponseList;
import com.deark.be.order.repository.OrderQuestionRepository;
import com.deark.be.store.domain.Store;
import com.deark.be.store.repository.StoreRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQuestionService {
    private final OrderQuestionRepository orderQuestionRepository;
    private final StoreRepository storeRepository;

    public OrderQuestionResponseList getOrderFormQuestions(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을 수 없습니다."));

        List<OrderQuestion> questions = orderQuestionRepository.findAllByStore(store);

        return OrderQuestionResponseList.from(
                questions.stream()
                        .map(OrderQuestionResponse::from)
                        .toList()
        );
    }
}
