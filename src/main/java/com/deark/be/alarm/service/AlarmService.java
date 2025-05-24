package com.deark.be.alarm.service;

import com.deark.be.alarm.domain.Alarm;
import com.deark.be.alarm.dto.request.DeleteAlarmRequest;
import com.deark.be.alarm.dto.request.ReadAlarmRequest;
import com.deark.be.alarm.dto.response.AlarmResponseList;
import com.deark.be.alarm.repository.AlarmRepository;
import com.deark.be.order.domain.type.OrderStatus;
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

    public AlarmResponseList getAlarmList(Long userId, OrderStatus orderStatus) {
        return alarmRepository.findAllByUserIdAndType(userId, orderStatus);
    }

    @Transactional
    public void markAlarmAsDeleted(DeleteAlarmRequest request) {
        request.alarmIdList().forEach(alarmId -> {
            alarmRepository.findById(alarmId).ifPresent(Alarm::markAsDeleted);
        });
    }

    @Transactional
    public void markAlarmAsRead(ReadAlarmRequest request) {
        request.alarmIdList().forEach(alarmId -> {
            alarmRepository.findById(alarmId).ifPresent(Alarm::markAsRead);
        });
    }
}
