package com.deark.be.design.repository.init;

import com.deark.be.design.domain.Design;
import com.deark.be.design.domain.Sheet;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.design.repository.SheetRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.store.domain.Store;
import com.deark.be.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(4)
@DummyDataInit
public class SheetInitializer implements ApplicationRunner {

    private final SheetRepository sheetRepository;
    private final StoreRepository storeRepository;
    private final DesignRepository designRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (sheetRepository.count() > 0) {
            log.info("[Sheet] 더미 데이터 존재");
        } else {
            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Design DESIGN1 = designRepository.findById(1L).orElseThrow();
            Design DESIGN2 = designRepository.findById(2L).orElseThrow();
            Design DESIGN3 = designRepository.findById(3L).orElseThrow();

            List<Sheet> sheetList = new ArrayList<>();

            Sheet DUMMY_SHEET = Sheet.builder()
                    .store(STORE1)
                    .design(DESIGN1)
                    .name("바닐라 시트")
                    .build();

            Sheet DUMMY_SHEET2 = Sheet.builder()
                    .store(STORE1)
                    .design(DESIGN2)
                    .name("초코 시트")
                    .build();

            Sheet DUMMY_SHEET3 = Sheet.builder()
                    .store(STORE1)
                    .design(DESIGN3)
                    .name("딸기 시트")
                    .build();

            sheetList.add(DUMMY_SHEET);
            sheetList.add(DUMMY_SHEET2);
            sheetList.add(DUMMY_SHEET3);

            sheetRepository.saveAll(sheetList);
        }
    }
}
