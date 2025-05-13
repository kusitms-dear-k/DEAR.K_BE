package com.deark.be.order.service;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.domain.type.Status;
import com.deark.be.order.dto.response.*;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MessageRepository messageRepository;
    private final QARepository qaRepository;
    private final UserService userService;

    public MyOrderCountResponseList getAllCountByStatus(Long userId) {
        User user = userService.findUser(userId);

        List<MyOrderCountResponse> result = new ArrayList<>();

        for (Status status : Status.values()) {
            Long count = messageRepository.countByUserAndStatus(user, status);
            result.add(MyOrderCountResponse.of(status, count));
        }

        return MyOrderCountResponseList.from(result);
    }

    public MyOrderStatusResponseList getAllMyOrdersByStatus(Long userId, Status status) {
        User user = userService.findUser(userId);

        List<Message> pendingMessages = messageRepository.findAllByUserAndStatus(user, status);

        List<MyOrderStatusResponse> responseList =  pendingMessages.stream()
                .map(message -> {
                    List<QA> qaList = qaRepository.findAllByMessage(message);
                    Map<String, String> qaMap = buildOrderedQaMap(qaList);
                    return MyOrderStatusResponse.of(message, qaMap);
                })
                .toList();

        return MyOrderStatusResponseList.from(responseList);
    }

    private static Map<String, String> buildOrderedQaMap(List<QA> qaList) {
        Map<String, String> rawMap = qaList.stream()
                .collect(Collectors.toMap(QA::getQuestion, QA::getAnswer, (a, b) -> b));

        Map<String, String> orderedMap = new LinkedHashMap<>();

        for (String key : QA_ORDER) {
            if (rawMap.containsKey(key)) {
                orderedMap.put(key, rawMap.get(key));
            }
        }

        rawMap.keySet().stream()
                .filter(k -> !orderedMap.containsKey(k))
                .forEach(k -> orderedMap.put(k, rawMap.get(k)));

        return orderedMap;
    }

    private static final List<String> QA_ORDER = List.of(
            "이름", "전화번호", "디자인", "크기", "크림 맛", "시트 맛", "픽업 희망 일자", "픽업 희망 시간", "추가 요청사항"
    );
}
