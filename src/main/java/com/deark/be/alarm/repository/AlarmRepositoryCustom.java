package com.deark.be.alarm.repository;

import com.deark.be.alarm.dto.response.AlarmResponseList;
import com.deark.be.order.domain.type.Status;

public interface AlarmRepositoryCustom {

    AlarmResponseList findAllByUserIdAndType(Long userId, Status status);
}
