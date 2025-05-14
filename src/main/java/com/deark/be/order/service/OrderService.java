package com.deark.be.order.service;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.domain.type.Status;
import com.deark.be.order.dto.response.*;
import com.deark.be.order.exception.OrderException;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import com.deark.be.store.service.BusinessHoursService;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
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

import static com.deark.be.order.exception.errorcode.OrderErrorCode.ORDER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

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
                    Map<String, String> qaMap = buildOrderedQaMap(qaList);
                    return MyOrderStatusResponse.of(message, qaMap);
                })
                .toList();

        return MyOrderStatusResponseList.from(responseList);
    }

    public MyOrderRejectedResponse getRejectedOrderReason(Long messageId) {
        Message message = findMessage(messageId);
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
}
