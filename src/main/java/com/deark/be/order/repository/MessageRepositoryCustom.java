package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.type.MakeStatus;
import java.util.List;

public interface MessageRepositoryCustom {
    List<OrderRequestForm> findMessagesWithQAsByUserIdAndProgressStatusIn(Long userId, List<MakeStatus> statuses);
}
