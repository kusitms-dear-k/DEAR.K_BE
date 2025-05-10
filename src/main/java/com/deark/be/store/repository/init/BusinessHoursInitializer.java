package com.deark.be.store.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.repository.BusinessHoursRepository;
import com.deark.be.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.deark.be.store.domain.type.BusinessDay.*;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class BusinessHoursInitializer implements ApplicationRunner {

    private final BusinessHoursRepository businessHoursRepository;
    private final StoreRepository storeRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (businessHoursRepository.count() > 0) {
            log.info("[BusinessHours] 더미 데이터 존재");
        } else {
            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Store STORE2 = storeRepository.findById(2L).orElseThrow();
            Store STORE3 = storeRepository.findById(3L).orElseThrow();
            Store STORE4 = storeRepository.findById(4L).orElseThrow();
            Store STORE5 = storeRepository.findById(5L).orElseThrow();
            Store STORE6 = storeRepository.findById(6L).orElseThrow();
            Store STORE7 = storeRepository.findById(7L).orElseThrow();

            List<BusinessHours> businessHoursList = new ArrayList<>();

            BusinessHours DUMMY_BUSINESS_HOURS1 = BusinessHours.builder()
                    .store(STORE1)
                    .businessDay(MONDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS2 = BusinessHours.builder()
                    .store(STORE1)
                    .businessDay(TUESDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS3 = BusinessHours.builder()
                    .store(STORE1)
                    .businessDay(WEDNESDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS4 = BusinessHours.builder()
                    .store(STORE1)
                    .businessDay(THURSDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS5 = BusinessHours.builder()
                    .store(STORE1)
                    .businessDay(FRIDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS6 = BusinessHours.builder()
                    .store(STORE2)
                    .businessDay(MONDAY)
                    .openTime(LocalTime.of(12, 0))
                    .closeTime(LocalTime.of(21, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS7 = BusinessHours.builder()
                    .store(STORE2)
                    .businessDay(TUESDAY)
                    .openTime(LocalTime.of(12, 0))
                    .closeTime(LocalTime.of(21, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS8 = BusinessHours.builder()
                    .store(STORE2)
                    .businessDay(WEDNESDAY)
                    .openTime(LocalTime.of(12, 0))
                    .closeTime(LocalTime.of(21, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS13 = BusinessHours.builder()
                    .store(STORE3)
                    .businessDay(WEDNESDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(20, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS14 = BusinessHours.builder()
                    .store(STORE3)
                    .businessDay(THURSDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(20, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS15 = BusinessHours.builder()
                    .store(STORE3)
                    .businessDay(FRIDAY)
                    .openTime(LocalTime.of(10, 0))
                    .closeTime(LocalTime.of(20, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS16 = BusinessHours.builder()
                    .store(STORE4)
                    .businessDay(MONDAY)
                    .openTime(LocalTime.of(13, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS18 = BusinessHours.builder()
                    .store(STORE4)
                    .businessDay(WEDNESDAY)
                    .openTime(LocalTime.of(13, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS20 = BusinessHours.builder()
                    .store(STORE4)
                    .businessDay(FRIDAY)
                    .openTime(LocalTime.of(13, 0))
                    .closeTime(LocalTime.of(22, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS22 = BusinessHours.builder()
                    .store(STORE5)
                    .businessDay(TUESDAY)
                    .openTime(LocalTime.of(11, 0))
                    .closeTime(LocalTime.of(17, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS24 = BusinessHours.builder()
                    .store(STORE5)
                    .businessDay(THURSDAY)
                    .openTime(LocalTime.of(11, 0))
                    .closeTime(LocalTime.of(17, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS26 = BusinessHours.builder()
                    .store(STORE6)
                    .businessDay(MONDAY)
                    .openTime(LocalTime.of(13, 0))
                    .closeTime(LocalTime.of(20, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS27 = BusinessHours.builder()
                    .store(STORE6)
                    .businessDay(TUESDAY)
                    .openTime(LocalTime.of(13, 0))
                    .closeTime(LocalTime.of(20, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS30 = BusinessHours.builder()
                    .store(STORE6)
                    .businessDay(FRIDAY)
                    .openTime(LocalTime.of(13, 0))
                    .closeTime(LocalTime.of(20, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS32 = BusinessHours.builder()
                    .store(STORE7)
                    .businessDay(TUESDAY)
                    .openTime(LocalTime.of(9, 0))
                    .closeTime(LocalTime.of(23, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS33 = BusinessHours.builder()
                    .store(STORE7)
                    .businessDay(WEDNESDAY)
                    .openTime(LocalTime.of(9, 0))
                    .closeTime(LocalTime.of(23, 0))
                    .isOpen24Hours(false)
                    .build();

            BusinessHours DUMMY_BUSINESS_HOURS35 = BusinessHours.builder()
                    .store(STORE7)
                    .businessDay(FRIDAY)
                    .openTime(LocalTime.of(9, 0))
                    .closeTime(LocalTime.of(23, 0))
                    .isOpen24Hours(false)
                    .build();

            businessHoursList.add(DUMMY_BUSINESS_HOURS1);
            businessHoursList.add(DUMMY_BUSINESS_HOURS2);
            businessHoursList.add(DUMMY_BUSINESS_HOURS3);
            businessHoursList.add(DUMMY_BUSINESS_HOURS4);
            businessHoursList.add(DUMMY_BUSINESS_HOURS5);
            businessHoursList.add(DUMMY_BUSINESS_HOURS6);
            businessHoursList.add(DUMMY_BUSINESS_HOURS7);
            businessHoursList.add(DUMMY_BUSINESS_HOURS8);
            businessHoursList.add(DUMMY_BUSINESS_HOURS13);
            businessHoursList.add(DUMMY_BUSINESS_HOURS14);
            businessHoursList.add(DUMMY_BUSINESS_HOURS15);
            businessHoursList.add(DUMMY_BUSINESS_HOURS16);
            businessHoursList.add(DUMMY_BUSINESS_HOURS18);
            businessHoursList.add(DUMMY_BUSINESS_HOURS20);
            businessHoursList.add(DUMMY_BUSINESS_HOURS22);
            businessHoursList.add(DUMMY_BUSINESS_HOURS24);
            businessHoursList.add(DUMMY_BUSINESS_HOURS26);
            businessHoursList.add(DUMMY_BUSINESS_HOURS27);
            businessHoursList.add(DUMMY_BUSINESS_HOURS30);
            businessHoursList.add(DUMMY_BUSINESS_HOURS32);
            businessHoursList.add(DUMMY_BUSINESS_HOURS33);
            businessHoursList.add(DUMMY_BUSINESS_HOURS35);

            businessHoursRepository.saveAll(businessHoursList);
        }
    }
}