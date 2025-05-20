package com.deark.be.alarm.service;

import com.deark.be.alarm.dto.response.AlarmResponseList;
import com.deark.be.alarm.repository.AlarmRepository;
import com.deark.be.order.domain.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public AlarmResponseList getAlarmList(Long userId, Status status) {
        return alarmRepository.findAllByUserIdAndType(userId, status);
    }
}
