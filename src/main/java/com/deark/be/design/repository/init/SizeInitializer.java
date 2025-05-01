package com.deark.be.design.repository.init;

import com.deark.be.design.domain.Design;
import com.deark.be.design.domain.Size;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.design.repository.SizeRepository;
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
public class SizeInitializer implements ApplicationRunner {

    private final SizeRepository sizeRepository;
    private final StoreRepository storeRepository;
    private final DesignRepository designRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (sizeRepository.count() > 0) {
            log.info("[Size] 더미 데이터 존재");
        } else {
            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Design DESIGN1 = designRepository.findById(1L).orElseThrow();
            Design DESIGN2 = designRepository.findById(2L).orElseThrow();
            Design DESIGN3 = designRepository.findById(3L).orElseThrow();

            List<Size> sizeList = new ArrayList<>();

            Size DUMMY_SIZE1 = Size.builder()
                    .store(STORE1)
                    .design(DESIGN1)
                    .name("도시락 케이크")
                    .build();

            Size DUMMY_SIZE2 = Size.builder()
                    .store(STORE1)
                    .design(DESIGN2)
                    .name("1호 케이크")
                    .build();

            Size DUMMY_SIZE3 = Size.builder()
                    .store(STORE1)
                    .design(DESIGN3)
                    .name("2호 케이크")
                    .build();

            sizeList.add(DUMMY_SIZE1);
            sizeList.add(DUMMY_SIZE2);
            sizeList.add(DUMMY_SIZE3);

            sizeRepository.saveAll(sizeList);
        }
    }
}
