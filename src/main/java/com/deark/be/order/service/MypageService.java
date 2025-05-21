package com.deark.be.order.service;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.domain.type.DesignType;
import com.deark.be.order.domain.type.ProgressStatus;
import com.deark.be.order.domain.type.Status;
import com.deark.be.order.dto.response.*;
import com.deark.be.order.exception.OrderException;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import com.deark.be.store.service.BusinessHoursService;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.deark.be.order.exception.errorcode.OrderErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageService {

    private final MessageRepository messageRepository;
    private final QARepository qaRepository;
    private final UserService userService;
    private final BusinessHoursService businessHoursService;

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
                    List<QAStatusResponse> qaStatusList = buildOrderedQaStatusList(qaList);
                    return MyOrderStatusResponse.of(message, qaStatusList);
                })
                .toList();

        return MyOrderStatusResponseList.from(responseList);
    }

    public MyOrderRejectedResponse getRejectedOrderReason(Long messageId) {
        Message message = findMessage(messageId);

        if (message.getStatus() != Status.REJECTED) {
            throw new OrderException(ORDER_NOT_REJECTED);
        }

        return MyOrderRejectedResponse.from(message);
    }

    public MyOrderDetailResponse getOrderDetail(Long messageId) {
        Message message = findMessage(messageId);

        List<QA> qaList = qaRepository.findAllByMessage(message);
        List<QAResponse> qaResponses = buildOrderedQaList(qaList);

        String pickupDateStr = qaResponses.stream()
                .filter(q -> "픽업 희망 일자".equals(q.title()))
                .map(QAResponse::answer)
                .findFirst()
                .orElse("");

        String dayName = extractDayName(pickupDateStr);
        String businessHourStr = getBusinessHourStr(dayName, message);

        return MyOrderDetailResponse.of(message, businessHourStr, qaResponses);
    }

    public MyOrderAcceptedResponse getAcceptedOrderDetail(Long messageId) {
        Message message = findMessage(messageId);

        if (message.getStatus() != Status.ACCEPTED) {
            throw new OrderException(ORDER_NOT_ACCEPTED);
        }

        List<QA> qaList = qaRepository.findAllByMessage(message);
        Map<String, String> qaMap = buildOrderedQaMap(qaList);

        return MyOrderAcceptedResponse.of(message, qaMap);
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

    private static List<QAStatusResponse> buildOrderedQaStatusList(List<QA> qaList) {
        Map<String, QA> rawMap = qaList.stream()
                .collect(Collectors.toMap(QA::getQuestion, Function.identity(), (a, b) -> b));

        List<QAStatusResponse> orderedList = new ArrayList<>();

        for (String key : QA_ORDER) {
            if (rawMap.containsKey(key)) {
                orderedList.add(QAStatusResponse.from(rawMap.get(key)));
            }
        }

        rawMap.keySet().stream()
                .filter(k -> !QA_ORDER.contains(k))
                .sorted()
                .forEach(k -> orderedList.add(QAStatusResponse.from(rawMap.get(k))));

        return orderedList;
    }


    private static List<QAResponse> buildOrderedQaList(List<QA> qaList) {
        Map<String, QA> qaMap = qaList.stream()
                .collect(Collectors.toMap(QA::getQuestion, Function.identity(), (a, b) -> b));

        List<QAResponse> orderedList = new ArrayList<>();

        for (String key : QA_ORDER) {
            if (qaMap.containsKey(key)) {
                orderedList.add(QAResponse.from(qaMap.get(key)));
            }
        }

        qaMap.keySet().stream()
                .filter(k -> !QA_ORDER.contains(k))
                .sorted()
                .forEach(k -> orderedList.add(QAResponse.from(qaMap.get(k))));

        return orderedList;
    }

    private static final List<String> QA_ORDER = List.of(
            "이름", "전화번호", "디자인", "크기", "크림 맛", "시트 맛", "픽업 희망 일자", "픽업 희망 시간", "추가 요청사항"
    );

    private static String extractDayName(String dateStr) {
        if (!StringUtils.hasText(dateStr)) {
            return null;
        }

        Matcher matcher = Pattern.compile("(\\d{4})[년\\-\\.\\s]*(\\d{1,2})[월\\-\\.\\s]*(\\d{1,2})").matcher(dateStr);

        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            LocalDate date = LocalDate.of(year, month, day);
            return date.getDayOfWeek().name();
        }

        return null;
    }

    private String getBusinessHourStr(String dayName, Message message) {
        return businessHoursService.getBusinessHourForPickupDate(message.getStore(), dayName);
    }

    private Message findMessage(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public OrderManagementResponseList getPickupScheduledOrders(Long userId) {
        return extractOrderManagementResponses(userId, List.of(ProgressStatus.RESERVED, ProgressStatus.BAKING));
    }

    @Transactional(readOnly = true)
    public OrderManagementResponseList getPickupCompletedOrders(Long userId) {
        return extractOrderManagementResponses(userId, List.of(ProgressStatus.PICKUP_DONE));
    }

    private OrderManagementResponseList extractOrderManagementResponses(Long userId, List<ProgressStatus> statuses) {
        List<Message> messages = messageRepository.findMessagesWithQAsByUserIdAndProgressStatusIn(userId, statuses);

        List<OrderManagementResponse> responses = messages.stream()
                .map(message -> {
                    String storeName = message.getStore().getName();

                    String designName = (message.getDesignType() == DesignType.CUSTOM)
                            ? "갤러리에서 추가한 디자인"
                            : message.getDesign().getName();

                    String designUrl = (message.getDesignType() == DesignType.CUSTOM)
                            ? message.getDesignUrl()
                            : message.getDesign().getImageUrl();

                    Map<String, String> answerMap = new HashMap<>();
                    for (QA qa : message.getQaList()) {
                        String question = qa.getQuestion();
                        String answer = qa.getAnswer();
                        if (!answerMap.containsKey(question)) {
                            answerMap.put(question, answer);
                        }
                    }

                    return OrderManagementResponse.builder()
                            .messageId(message.getId())
                            .storeName(storeName)
                            .designName(designName)
                            .designUrl(designUrl)
                            .size(answerMap.get("크기"))
                            .cream(answerMap.get("크림"))
                            .sheet(answerMap.get("시트"))
                            .pickupDate(answerMap.get("픽업 희망 일자"))
                            .pickupTime(answerMap.get("픽업 희망 시간"))
                            .progressStatus(message.getProgressStatus())
                            .build();
                })
                .toList();

        return OrderManagementResponseList.from(responses);
    }
}