package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.type.ProgressStatus;
import java.util.List;

public interface MessageRepositoryCustom {
    List<OrderRequestForm> findMessagesWithQAsByUserIdAndProgressStatusIn(Long userId, List<ProgressStatus> statuses);
}
