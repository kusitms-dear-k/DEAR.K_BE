package com.deark.be.order.service;

import com.deark.be.order.domain.type.Status;
import com.deark.be.order.dto.response.MyOrderCountResponse;
import com.deark.be.order.dto.response.MyOrderCountResponseList;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MessageRepository messageRepository;
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
}
