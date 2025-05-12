package com.deark.be.store.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.store.domain.Store;
import com.deark.be.store.repository.StoreRepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@DummyDataInit
public class StoreInitializer implements ApplicationRunner {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Value("${spring.cloud.aws.s3.url}/store")
    private String storeImageUrl;

    @Override
    public void run(ApplicationArguments args) {
        if (storeRepository.count() > 0) {
            log.info("[Store] 더미 데이터 존재");
        } else {
            User OWNER1 = userRepository.findById(2L).orElseThrow();
            User OWNER2 = userRepository.findById(3L).orElseThrow();
            User OWNER3 = userRepository.findById(4L).orElseThrow();
            User OWNER4 = userRepository.findById(5L).orElseThrow();
            User OWNER5 = userRepository.findById(6L).orElseThrow();
            User OWNER6 = userRepository.findById(7L).orElseThrow();

            List<Store> storeList = new ArrayList<>();

            Store DUMMY_STORE1 = Store.builder()
                    .user(OWNER1)
                    .name("디어레터")
                    .description("서울 강남구에 위치한 디어레터는 프리미엄 레터링 케이크 전문점입니다. 고객 한 사람 한 사람의 특별한 순간을 위해 정성스럽게 케이크를 제작합니다. 생일, 웨딩, 프로포즈, 기업 행사까지 다양한 맞춤형 주문을 받고 있습니다.")
                    .address("서울특별시 강남구 역삼동 123-45")
                    .phone("02-1234-5678")
                    .businessNumber("123-45-67890")
                    .establishDate(LocalDate.of(2020, 1, 1))
                    .imageUrl(storeImageUrl + "/blueberry.jpg")
                    .isSelfService(true)
                    .isSameDayOrder(true)
                    .build();

            Store DUMMY_STORE2 = Store.builder()
                    .user(OWNER2)
                    .name("모먼트케이크")
                    .description("모먼트케이크는 동작구에 위치한 감성 레터링 케이크 카페입니다. 중요한 순간마다 함께할 수 있는 의미 있는 케이크를 만들어드립니다. 100% 예약제로 운영되며, 사전 디자인 상담도 가능합니다.")
                    .address("서울특별시 동작구 상도동 456-78")
                    .phone("02-2345-6789")
                    .businessNumber("234-56-78901")
                    .establishDate(LocalDate.of(2021, 7, 2))
                    .imageUrl(storeImageUrl + "/birthday.jpg")
                    .isSelfService(false)
                    .isSameDayOrder(false)
                    .build();

            Store DUMMY_STORE3 = Store.builder()
                    .user(OWNER3)
                    .name("썸띵스위트")
                    .description("썸띵스위트는 송파구에 위치한 수제 케이크 공방으로, 모든 케이크를 신선한 재료와 직접 만든 생크림을 사용하여 제작합니다. 맞춤 문구 요청 가능하며, 미니케이크와 기념일 세트도 준비되어 있습니다.")
                    .address("서울특별시 송파구 잠실7동 145")
                    .phone("02-3456-7890")
                    .businessNumber("345-67-89012")
                    .establishDate(LocalDate.of(2019, 5, 15))
                    .imageUrl(storeImageUrl + "/yellow.jpg")
                    .isSelfService(false)
                    .isSameDayOrder(false)
                    .build();

            Store DUMMY_STORE4 = Store.builder()
                    .user(OWNER4)
                    .name("스위트어게인")
                    .description("스위트어게인은 강서구 공항대로 인근에 위치한 모던한 레터링 케이크 전문점입니다. 다양한 컬러 테마, 글씨체 커스터마이징, 그리고 빠른 당일 제작 서비스를 제공합니다.")
                    .address("서울특별시 강서구 마곡동 에이스타운 1층")
                    .phone("02-4567-8901")
                    .businessNumber("456-78-90123")
                    .establishDate(LocalDate.of(2023, 3, 10))
                    .imageUrl(storeImageUrl + "/star.jpg")
                    .isSelfService(true)
                    .isSameDayOrder(true)
                    .build();

            Store DUMMY_STORE5 = Store.builder()
                    .user(OWNER5)
                    .name("마이레터")
                    .description("마이레터는 강북구 미아동에 있는 포토·레터링 케이크 전문 공방입니다. 사진을 넣은 케이크, 문구 케이크, 특별한 테마 케이크까지 다양한 스타일을 제공합니다. 소량 제작 원칙으로 항상 신선한 케이크를 보장합니다.")
                    .address("서울특별시 강북구 미아동 벽산타워 4층")
                    .phone("02-5678-9012")
                    .businessNumber("567-89-01234")
                    .establishDate(LocalDate.of(2024, 8, 20))
                    .imageUrl(storeImageUrl + "/ribbon.jpg")
                    .isSelfService(false)
                    .isSameDayOrder(false)
                    .build();

            Store DUMMY_STORE6 = Store.builder()
                    .user(OWNER6)
                    .name("소프트케이크")
                    .description("소프트케이크는 강동구 천호동에 자리 잡은 소프트컬러 레터링 케이크 전문점입니다. 부드러운 색감과 심플한 디자인으로 특별한 날을 더욱 우아하게 만들어드립니다. 비건 케이크 라인업도 제공 중입니다.")
                    .address("서울특별시 강동구 천호동 숭인빌라 2층")
                    .phone("02-6789-0123")
                    .businessNumber("678-90-12345")
                    .establishDate(LocalDate.of(2016, 12, 25))
                    .imageUrl(storeImageUrl + "/loopy.png")
                    .isSelfService(true)
                    .isSameDayOrder(true)
                    .build();

            Store DUMMY_STORE7 = Store.builder()
                    .user(OWNER1)
                    .name("루프스튜디오")
                    .description("루프스튜디오는 중랑구의 아늑한 케이크 공방으로, 감각적인 레터링과 미니 케이크가 인기입니다. 따뜻한 감성의 매장에서 케이크 주문 및 수령이 가능합니다. 특히 커스텀 디자인에 강점을 가지고 있습니다.")
                    .address("서울특별시 중랑구 면목동 루프타워 10층")
                    .phone("02-7890-1234")
                    .businessNumber("789-01-23456")
                    .establishDate(LocalDate.of(2019, 11, 30))
                    .imageUrl(storeImageUrl + "/blackpink.png")
                    .isSelfService(false)
                    .isSameDayOrder(false)
                    .build();

            Store DUMMY_STORE8 = Store.builder()
                    .user(OWNER2)
                    .name("비터스윗")
                    .description("비터스윗은 감성 가득한 문구 케이크로 유명한 중랑구의 케이크숍입니다. 따뜻한 색감, 손글씨 느낌의 레터링이 특징이며, 매 시즌마다 새로운 테마 케이크를 선보입니다.")
                    .address("서울특별시 중랑구 망우동 양진빌라 6층")
                    .phone("02-7891-2345")
                    .businessNumber("791-02-34567")
                    .establishDate(LocalDate.of(2020, 4, 5))
                    .imageUrl(storeImageUrl + "/whiteblack.png")
                    .isSelfService(false)
                    .isSameDayOrder(true)
                    .build();

            Store DUMMY_STORE9 = Store.builder()
                    .user(OWNER3)
                    .name("미스틱케이크")
                    .description("미스틱케이크는 유니크한 스타일의 레터링 케이크를 제작하는 중구의 힙한 매장입니다. 색감과 폰트에 특별한 포인트를 주어 젊은 층 사이에서 인기가 높습니다. 단체 주문, 행사 주문도 가능합니다.")
                    .address("서울특별시 중구 신당동 24-1")
                    .phone("02-7892-3456")
                    .businessNumber("792-03-45678")
                    .establishDate(LocalDate.of(2021, 2, 14))
                    .imageUrl(storeImageUrl + "/muffin.png")
                    .isSelfService(true)
                    .isSameDayOrder(false)
                    .build();

            Store DUMMY_STORE10 = Store.builder()
                    .user(OWNER4)
                    .name("그레이문")
                    .description("그레이문은 우아하고 세련된 레터링 케이크를 만드는 구로구의 인기 매장입니다. 그레이, 베이지 톤의 미니멀 디자인을 주로 사용하며, 프라이빗 오더가 가능합니다. 품질 높은 원재료만을 고집합니다.")
                    .address("서울특별시 구로구 신도림동 834-12")
                    .phone("02-7893-4567")
                    .businessNumber("793-04-56789")
                    .establishDate(LocalDate.of(2022, 9, 9))
                    .imageUrl(storeImageUrl + "/pink.png")
                    .isSelfService(false)
                    .isSameDayOrder(true)
                    .build();

            storeList.add(DUMMY_STORE1);
            storeList.add(DUMMY_STORE2);
            storeList.add(DUMMY_STORE3);
            storeList.add(DUMMY_STORE4);
            storeList.add(DUMMY_STORE5);
            storeList.add(DUMMY_STORE6);
            storeList.add(DUMMY_STORE7);
            storeList.add(DUMMY_STORE8);
            storeList.add(DUMMY_STORE9);
            storeList.add(DUMMY_STORE10);

            storeRepository.saveAll(storeList);
        }
    }
}