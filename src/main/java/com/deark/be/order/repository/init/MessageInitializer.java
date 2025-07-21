package com.deark.be.order.repository.init;

import com.deark.be.design.domain.CakeDesign;
import com.deark.be.design.repository.CakeDesignRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.repository.MessageRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.deark.be.order.domain.type.OrderStatus.*;

@Slf4j
@RequiredArgsConstructor
@Order(5)
@DummyDataInit
public class MessageInitializer implements ApplicationRunner {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final CakeDesignRepository cakeDesignRepository;

    @Value("${spring.cloud.aws.s3.url}/design")
    private String designImageUrl;

    @Override
    public void run(ApplicationArguments args) {
        if (messageRepository.count() > 0) {
            log.info("[Message] 더미 데이터 존재");
        } else {
            User USER1 = userRepository.findById(1L).orElseThrow();

            Store STORE1 = storeRepository.findById(1L).orElseThrow();
            Store STORE2 = storeRepository.findById(2L).orElseThrow();
            Store STORE3 = storeRepository.findById(3L).orElseThrow();

            CakeDesign CakeDESIGN1 = cakeDesignRepository.findById(1L).orElseThrow();
            CakeDesign CakeDESIGN2 = cakeDesignRepository.findById(2L).orElseThrow();
            CakeDesign CakeDESIGN3 = cakeDesignRepository.findById(3L).orElseThrow();
            CakeDesign CakeDESIGN8 = cakeDesignRepository.findById(8L).orElseThrow();

            List<OrderRequestForm> orderRequestFormList = new ArrayList<>();

            OrderRequestForm DUMMY_OrderRequestForm1 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE1)
                    .cakeDesign(CakeDESIGN1)
                    .orderStatus(PENDING)
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm2 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE1)
                    .cakeDesign(CakeDESIGN8)
                    .orderStatus(ACCEPTED)
                    .makerResponse("24500")
                    .responseTime(LocalDateTime.of(2025, 5, 19, 12, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm3 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE1)
                    .cakeDesign(CakeDESIGN1)
                    .orderStatus(REJECTED)
                    .makerResponse("디자인에 사용될 재료 부족 및 소진")
                    .responseTime(LocalDateTime.of(2025, 5, 20, 12, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm4 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE2)
                    .cakeDesign(CakeDESIGN2)
                    .orderStatus(PENDING)
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm5 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE2)
                    .cakeDesign(CakeDESIGN2)
                    .orderStatus(ACCEPTED)
                    .makerResponse("38000")
                    .responseTime(LocalDateTime.of(2025, 5, 21, 17, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm6 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE2)
                    .cakeDesign(CakeDESIGN2)
                    .orderStatus(REJECTED)
                    .makerResponse("기간 내 제작 불가")
                    .responseTime(LocalDateTime.of(2025, 5, 18, 14, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm7 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE3)
                    .cakeDesign(CakeDESIGN3)
                    .orderStatus(ACCEPTED)
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm8 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE3)
                    .cakeDesign(CakeDESIGN3)
                    .orderStatus(ACCEPTED)
                    .makerResponse("50000")
                    .responseTime(LocalDateTime.of(2025, 5, 19, 10, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm9 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE1)
                    .cakeDesign(CakeDESIGN1)
                    .makerResponse("26500")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 18, 11, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm10 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE1)
                    .cakeDesign(CakeDESIGN8)
                    .makerResponse("34500")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 20, 13, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm11 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE2)
                    .cakeDesign(CakeDESIGN2)
                    .makerResponse("40000")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 22, 15, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm12 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE3)
                    .cakeDesign(CakeDESIGN3)
                    .makerResponse("55000")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 23, 16, 0))
                    .build();

            OrderRequestForm DUMMY_OrderRequestForm13 = OrderRequestForm.builder()
                    .user(USER1)
                    .store(STORE1)
                    .cakeDesign(CakeDESIGN1)
                    .makerResponse("30000")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 24, 18, 0))
                    .build();

            orderRequestFormList.add(DUMMY_OrderRequestForm1);
            orderRequestFormList.add(DUMMY_OrderRequestForm2);
            orderRequestFormList.add(DUMMY_OrderRequestForm3);
            orderRequestFormList.add(DUMMY_OrderRequestForm4);
            orderRequestFormList.add(DUMMY_OrderRequestForm5);
            orderRequestFormList.add(DUMMY_OrderRequestForm6);
            orderRequestFormList.add(DUMMY_OrderRequestForm7);
            orderRequestFormList.add(DUMMY_OrderRequestForm8);
            orderRequestFormList.add(DUMMY_OrderRequestForm9);
            orderRequestFormList.add(DUMMY_OrderRequestForm10);
            orderRequestFormList.add(DUMMY_OrderRequestForm11);
            orderRequestFormList.add(DUMMY_OrderRequestForm12);
            orderRequestFormList.add(DUMMY_OrderRequestForm13);

            messageRepository.saveAll(orderRequestFormList);
        }
    }
}