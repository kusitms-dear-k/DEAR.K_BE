package com.deark.be.design.repository.init;

import com.deark.be.design.domain.Design;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.store.domain.Store;
import com.deark.be.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class DesignInitializer implements ApplicationRunner {

    private final DesignRepository designRepository;
    private final StoreRepository storeRepository;

    @Value("${spring.cloud.aws.s3.url}/design")
    private String designImageUrl;

    @Override
    public void run(ApplicationArguments args) {
        if (designRepository.count() > 0) {
            log.info("[Design] 더미 데이터 존재");
        } else {
            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Store STORE2 = storeRepository.findById(2L).orElseThrow();
            Store STORE3 = storeRepository.findById(3L).orElseThrow();
            Store STORE4 = storeRepository.findById(4L).orElseThrow();
            Store STORE5 = storeRepository.findById(5L).orElseThrow();
            Store STORE6 = storeRepository.findById(6L).orElseThrow();
            Store STORE7 = storeRepository.findById(7L).orElseThrow();

            List<Design> designList = new ArrayList<>();

            Design DUMMY_DESIGN1 = Design.builder()
                    .store(STORE1)
                    .name("루피 케이크")
                    .description("루피를 테마로 한 귀여운 케이크입니다.")
                    .price(20000L)
                    .imageUrl(designImageUrl + "/loopy.jpg")
                    .build();

            Design DUMMY_DESIGN2 = Design.builder()
                    .store(STORE2)
                    .name("소방관 케이크")
                    .description("소방관 취업 축하 케이크입니다.")
                    .price(25000L)
                    .imageUrl(designImageUrl + "/fire.jpg")
                    .build();

            Design DUMMY_DESIGN3 = Design.builder()
                    .store(STORE3)
                    .name("블랙리본 케이크")
                    .description("슬픔을 위로하는 블랙리본 케이크입니다.")
                    .price(30000L)
                    .imageUrl(designImageUrl + "/blackribbon.jpg")
                    .build();

            Design DUMMY_DESIGN4 = Design.builder()
                    .store(STORE4)
                    .name("보라 케이크")
                    .description("보라색을 테마로 한 케이크입니다.")
                    .price(25000L)
                    .imageUrl(designImageUrl + "/purple.jpg")
                    .build();

            Design DUMMY_DESIGN5 = Design.builder()
                    .store(STORE5)
                    .name("생일 축하 그린 케이크")
                    .description("생일 축하를 위한 그린 케이크입니다. 위에 문구를 수정이 가능합니다.")
                    .price(28000L)
                    .imageUrl(designImageUrl + "/green.jpg")
                    .build();

            Design DUMMY_DESIGN6 = Design.builder()
                    .store(STORE6)
                    .name("블루 케이크")
                    .description("곰돌이가 함께 올라간 블루색을 테마로 한 케이크입니다.")
                    .price(32000L)
                    .imageUrl(designImageUrl + "/blue.jpg")
                    .build();

            Design DUMMY_DESIGN7 = Design.builder()
                    .store(STORE7)
                    .name("블랙 민트 케이크")
                    .description("블랙에 문구를 민트 색으로 올려 포인트를 준 깔끔하지만 세련된 케이크입니다.")
                    .price(27000L)
                    .imageUrl(designImageUrl + "/mint.jpg")
                    .build();

            Design DUMMY_DESIGN8 = Design.builder()
                    .store(STORE1)
                    .name("레인보우 케이크")
                    .description("다양한 색상의 레인보우 케이크입니다. 색상은 자유롭게 변경 가능합니다.")
                    .price(35000L)
                    .imageUrl(designImageUrl + "/rainbow.jpg")
                    .build();

            designList.add(DUMMY_DESIGN1);
            designList.add(DUMMY_DESIGN2);
            designList.add(DUMMY_DESIGN3);
            designList.add(DUMMY_DESIGN4);
            designList.add(DUMMY_DESIGN5);
            designList.add(DUMMY_DESIGN6);
            designList.add(DUMMY_DESIGN7);
            designList.add(DUMMY_DESIGN8);

            designRepository.saveAll(designList);
        }
    }
}
