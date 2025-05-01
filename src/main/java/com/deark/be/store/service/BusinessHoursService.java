package com.deark.be.store.service;

import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.dto.request.BusinessHoursRequest;
import com.deark.be.store.repository.BusinessHoursRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// BusinessHourService.java
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessHoursService {

    private final BusinessHoursRepository businessHoursRepository;

    @Transactional
    public void registerBusinessHours(Store store, List<BusinessHoursRequest> requests) {
        businessHoursRepository.deleteByStore(store); // ✅ 모든 요일 데이터 삭제하고 재설정

        List<BusinessHours> hours = requests.stream()
                .map(r -> BusinessHours.builder()
                        .store(store)
                        .businessDay(r.businessDay())
                        .openTime(r.openTime())
                        .closeTime(r.closeTime())
                        .isOpen24Hours(r.isOpen24Hours())
                        .build())
                .toList();

        businessHoursRepository.saveAll(hours);
    }
}