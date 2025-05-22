package com.deark.be.design.repository.init;

import com.deark.be.design.domain.Cream;
import com.deark.be.design.domain.Design;
import com.deark.be.design.repository.CreamRepository;
import com.deark.be.design.repository.DesignRepository;
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
@Order(5)
@DummyDataInit
public class CreamInitializer implements ApplicationRunner {

    private final CreamRepository creamRepository;
    private final StoreRepository storeRepository;
    private final DesignRepository designRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (creamRepository.count() > 0) {
            log.info("[Cream] 더미 데이터 존재");
        } else {
            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Design DESIGN1 = designRepository.findById(1L).orElseThrow();

            Store STORE2 = storeRepository.findById(2L).orElseThrow();
            Design DESIGN2 = designRepository.findById(2L).orElseThrow();

            Store STORE3 = storeRepository.findById(3L).orElseThrow();
            Design DESIGN3 = designRepository.findById(3L).orElseThrow();

            List<Cream> creamList = new ArrayList<>();

            Cream DUMMY_CREAM1 = Cream.builder()
                    .store(STORE1)
                    .design(DESIGN1)
                    .name("바닐라 크림")
                    .build();

            Cream DUMMY_CREAM2 = Cream.builder()
                    .store(STORE1)
                    .design(DESIGN1)
                    .name("초코 크림")
                    .build();

            Cream DUMMY_CREAM3 = Cream.builder()
                    .store(STORE1)
                    .design(DESIGN1)
                    .name("딸기 크림")
                    .build();

            Cream DUMMY_CREAM4 = Cream.builder()
                    .store(STORE2)
                    .design(DESIGN2)
                    .name("생크림")
                    .build();

            Cream DUMMY_CREAM5 = Cream.builder()
                    .store(STORE2)
                    .design(DESIGN2)
                    .name("초코 크림")
                    .build();

            Cream DUMMY_CREAM6 = Cream.builder()
                    .store(STORE2)
                    .design(DESIGN2)
                    .name("블루베리 크림")
                    .build();

            Cream DUMMY_CREAM7 = Cream.builder()
                    .store(STORE3)
                    .design(DESIGN3)
                    .name("딸기 크림")
                    .build();

            Cream DUMMY_CREAM8 = Cream.builder()
                    .store(STORE3)
                    .design(DESIGN3)
                    .name("초코 크림")
                    .build();

            creamList.add(DUMMY_CREAM1);
            creamList.add(DUMMY_CREAM2);
            creamList.add(DUMMY_CREAM3);
            creamList.add(DUMMY_CREAM4);
            creamList.add(DUMMY_CREAM5);
            creamList.add(DUMMY_CREAM6);
            creamList.add(DUMMY_CREAM7);
            creamList.add(DUMMY_CREAM8);

            creamRepository.saveAll(creamList);
        }
    }
}
