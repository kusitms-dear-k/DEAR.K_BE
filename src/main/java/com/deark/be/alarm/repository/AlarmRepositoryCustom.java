package com.deark.be.alarm.repository;

import com.deark.be.alarm.dto.response.AlarmResponseList;
import com.deark.be.order.domain.type.OrderStatus;

public interface AlarmRepositoryCustom {

    AlarmResponseList findAllByUserIdAndType(Long userId, OrderStatus orderStatus);
}
