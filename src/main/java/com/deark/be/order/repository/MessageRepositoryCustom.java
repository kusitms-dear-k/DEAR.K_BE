package com.deark.be.order.repository;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.ProgressStatus;
import java.util.List;

public interface MessageRepositoryCustom {
    List<Message> findMessagesWithQAsByUserIdAndProgressStatusIn(Long userId, List<ProgressStatus> statuses);
}
