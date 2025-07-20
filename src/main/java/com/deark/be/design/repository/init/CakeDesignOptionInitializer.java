package com.deark.be.design.repository.init;

import com.deark.be.design.domain.CakeDesign;
import com.deark.be.design.domain.CakeDesignOption;
import com.deark.be.design.domain.type.OptionCategory;
import com.deark.be.design.repository.CakeDesignRepository;
import com.deark.be.design.repository.CakeDesignOptionRepository;
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
public class CakeDesignOptionInitializer implements ApplicationRunner {

    private final CakeDesignRepository cakeDesignRepository;
    private final StoreRepository storeRepository;
    private final CakeDesignOptionRepository cakeDesignOptionRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (cakeDesignOptionRepository.count() > 0) {
            log.info("[CakeDesignOption] 더미 데이터 존재");
        } else {
            CakeDesign CakeDESIGN1 = cakeDesignRepository.findById(1L).orElseThrow();
            CakeDesign CakeDESIGN2 = cakeDesignRepository.findById(2L).orElseThrow();
            CakeDesign CakeDESIGN3 = cakeDesignRepository.findById(3L).orElseThrow();

            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Store STORE2 = storeRepository.findById(2L).orElseThrow();
            Store STORE3 = storeRepository.findById(3L).orElseThrow();

            List<CakeDesignOption> cakeDesignOptionList = new ArrayList<>();

            CakeDesignOption DUMMY_CREAM1 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.CREAM)
                    .name("바닐라 크림")
                    .build();
            CakeDesignOption DUMMY_CREAM2 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.CREAM)
                    .name("초코 크림")
                    .build();
            CakeDesignOption DUMMY_CREAM3 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.CREAM)
                    .name("딸기 크림")
                    .build();

            CakeDesignOption DUMMY_CREAM4 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.CREAM)
                    .name("생크림")
                    .build();
            CakeDesignOption DUMMY_CREAM5 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.CREAM)
                    .name("초코 크림")
                    .build();
            CakeDesignOption DUMMY_CREAM6 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.CREAM)
                    .name("블루베리 크림")
                    .build();

            CakeDesignOption DUMMY_CREAM7 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN3)
                    .store(STORE3)
                    .optionCategory(OptionCategory.CREAM)
                    .name("딸기 크림")
                    .build();
            CakeDesignOption DUMMY_CREAM8 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN3)
                    .store(STORE3)
                    .optionCategory(OptionCategory.CREAM)
                    .name("초코 크림")
                    .build();

            CakeDesignOption DUMMY_SIZE1 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.SIZE)
                    .name("도시락 케이크")
                    .build();
            CakeDesignOption DUMMY_SIZE2 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.SIZE)
                    .name("1호 케이크")
                    .build();
            CakeDesignOption DUMMY_SIZE3 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.SIZE)
                    .name("2호 케이크")
                    .build();

            CakeDesignOption DUMMY_SIZE4 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.SIZE)
                    .name("도시락 케이크")
                    .build();
            CakeDesignOption DUMMY_SIZE5 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.SIZE)
                    .name("1호 케이크")
                    .build();
            CakeDesignOption DUMMY_SIZE6 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.SIZE)
                    .name("2호 케이크")
                    .build();

            CakeDesignOption DUMMY_SIZE7 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN3)
                    .store(STORE3)
                    .optionCategory(OptionCategory.SIZE)
                    .name("도시락 케이크")
                    .build();
            CakeDesignOption DUMMY_SIZE8 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN3)
                    .store(STORE3)
                    .optionCategory(OptionCategory.SIZE)
                    .name("1호 케이크")
                    .build();

            CakeDesignOption DUMMY_SHEET1 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.SHEET)
                    .name("바닐라")
                    .build();
            CakeDesignOption DUMMY_SHEET2 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.SHEET)
                    .name("초코")
                    .build();
            CakeDesignOption DUMMY_SHEET3 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN1)
                    .store(STORE1)
                    .optionCategory(OptionCategory.SHEET)
                    .name("딸기")
                    .build();

            CakeDesignOption DUMMY_SHEET4 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.SHEET)
                    .name("바닐라")
                    .build();
            CakeDesignOption DUMMY_SHEET5 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN2)
                    .store(STORE2)
                    .optionCategory(OptionCategory.SHEET)
                    .name("바나나")
                    .build();

            CakeDesignOption DUMMY_SHEET6 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN3)
                    .store(STORE3)
                    .optionCategory(OptionCategory.SHEET)
                    .name("초코")
                    .build();
            CakeDesignOption DUMMY_SHEET7 = CakeDesignOption.builder()
                    .cakeDesign(CakeDESIGN3)
                    .store(STORE3)
                    .optionCategory(OptionCategory.SHEET)
                    .name("얼그레이")
                    .build();

            cakeDesignOptionList.add(DUMMY_CREAM1);
            cakeDesignOptionList.add(DUMMY_CREAM2);
            cakeDesignOptionList.add(DUMMY_CREAM3);
            cakeDesignOptionList.add(DUMMY_CREAM4);
            cakeDesignOptionList.add(DUMMY_CREAM5);
            cakeDesignOptionList.add(DUMMY_CREAM6);
            cakeDesignOptionList.add(DUMMY_CREAM7);
            cakeDesignOptionList.add(DUMMY_CREAM8);

            cakeDesignOptionList.add(DUMMY_SIZE1);
            cakeDesignOptionList.add(DUMMY_SIZE2);
            cakeDesignOptionList.add(DUMMY_SIZE3);
            cakeDesignOptionList.add(DUMMY_SIZE4);
            cakeDesignOptionList.add(DUMMY_SIZE5);
            cakeDesignOptionList.add(DUMMY_SIZE6);
            cakeDesignOptionList.add(DUMMY_SIZE7);
            cakeDesignOptionList.add(DUMMY_SIZE8);

            cakeDesignOptionList.add(DUMMY_SHEET1);
            cakeDesignOptionList.add(DUMMY_SHEET2);
            cakeDesignOptionList.add(DUMMY_SHEET3);
            cakeDesignOptionList.add(DUMMY_SHEET4);
            cakeDesignOptionList.add(DUMMY_SHEET5);
            cakeDesignOptionList.add(DUMMY_SHEET6);
            cakeDesignOptionList.add(DUMMY_SHEET7);

            cakeDesignOptionRepository.saveAll(cakeDesignOptionList);
        }
    }
}
