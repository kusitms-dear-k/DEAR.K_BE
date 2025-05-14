package com.deark.be.store.service;

import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.domain.type.BusinessDay;
import com.deark.be.store.dto.request.BusinessHoursRequest;
import com.deark.be.store.repository.BusinessHoursRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessHoursService {

    private final BusinessHoursRepository businessHoursRepository;

    @Transactional
    public void registerBusinessHours(Store store, List<BusinessHoursRequest> requests) {
        store.clearBusinessHours();// 기존 연관관계 제거

        for (BusinessHoursRequest request : requests) {
            BusinessHours bh = request.toEntity(store);
            store.addBusinessHour(bh); // 양방향 관계 설정
        }

        businessHoursRepository.saveAll(store.getBusinessHoursList());
    }

    public String getBusinessHourForPickupDate(Store store, String pickupDayName) {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(pickupDayName.trim().toUpperCase());
        BusinessDay businessDay = BusinessDay.fromDayOfWeek(dayOfWeek);

        return store.getBusinessHoursList().stream()
                .filter(bh -> bh.getBusinessDay() == businessDay)
                .findFirst()
                .map(bh -> bh.getOpenTime() + " ~ " + bh.getCloseTime())
                .orElse("해당 요일에는 운영하지 않습니다");
    }
}