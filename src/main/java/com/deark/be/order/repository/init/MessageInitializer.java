package com.deark.be.order.repository.init;

import com.deark.be.design.domain.Design;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.ProgressStatus;
import com.deark.be.order.domain.type.RequestDetailType;
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

import static com.deark.be.order.domain.type.DesignType.CUSTOM;
import static com.deark.be.order.domain.type.DesignType.STORE;
import static com.deark.be.order.domain.type.RequestDetailType.EVENT;
import static com.deark.be.order.domain.type.OrderStatus.*;
import static com.deark.be.order.domain.type.ResponseStatus.*;

@Slf4j
@RequiredArgsConstructor
@Order(5)
@DummyDataInit
public class MessageInitializer implements ApplicationRunner {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final DesignRepository designRepository;

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

            Design DESIGN1 = designRepository.findById(1L).orElseThrow();
            Design DESIGN2 = designRepository.findById(2L).orElseThrow();
            Design DESIGN3 = designRepository.findById(3L).orElseThrow();
            Design DESIGN8 = designRepository.findById(8L).orElseThrow();

            List<Message> messageList = new ArrayList<>();

            Message DUMMY_MESSAGE1 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN1)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN2)
                    .requestDetailType(EVENT)
                    .orderStatus(PENDING)
                    .responseStatus(UNRESPONSIVE)
                    .build();

            Message DUMMY_MESSAGE2 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN8)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN3)
                    .requestDetailType(EVENT)
                    .orderStatus(ACCEPTED)
                    .progressStatus(ProgressStatus.RESERVED)
                    .makerResponse("24500")
                    .responseTime(LocalDateTime.of(2025, 5, 19, 12, 0))
                    .responseStatus(UNRESPONSIVE)
                    .build();

            Message DUMMY_MESSAGE3 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN1)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN8)
                    .requestDetailType(EVENT)
                    .orderStatus(REJECTED)
                    .makerResponse("디자인에 사용될 재료 부족 및 소진")
                    .responseTime(LocalDateTime.of(2025, 5, 20, 12, 0))
                    .responseStatus(UNRESPONSIVE)
                    .build();

            Message DUMMY_MESSAGE4 = Message.builder()
                    .user(USER1)
                    .store(STORE2)
                    .design(DESIGN2)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN8)
                    .requestDetailType(EVENT)
                    .requestDetailDesign(DESIGN1)
                    .orderStatus(PENDING)
                    .responseStatus(UNRESPONSIVE)
                    .build();

            Message DUMMY_MESSAGE5 = Message.builder()
                    .user(USER1)
                    .store(STORE2)
                    .design(DESIGN2)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN1)
                    .requestDetailType(EVENT)
                    .orderStatus(ACCEPTED)
                    .makerResponse("38000")
                    .responseTime(LocalDateTime.of(2025, 5, 21, 17, 0))
                    .progressStatus(ProgressStatus.BAKING)
                    .responseStatus(PAID)
                    .build();

            Message DUMMY_MESSAGE6 = Message.builder()
                    .user(USER1)
                    .store(STORE2)
                    .design(DESIGN2)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN8)
                    .requestDetailType(EVENT)
                    .orderStatus(REJECTED)
                    .makerResponse("기간 내 제작 불가")
                    .responseTime(LocalDateTime.of(2025, 5, 18, 14, 0))
                    .responseStatus(UNRESPONSIVE)
                    .build();

            Message DUMMY_MESSAGE7 = Message.builder()
                    .user(USER1)
                    .store(STORE3)
                    .designType(CUSTOM)
                    .designUrl(designImageUrl + "/bear.png")
                    .requestDetailType(RequestDetailType.CUSTOM)
                    .requestDetailImageUrl(designImageUrl+"/heart.png")
                    .orderStatus(ACCEPTED)
                    .progressStatus(ProgressStatus.PICKUP_DONE)
                    .responseStatus(PAID)
                    .build();

            Message DUMMY_MESSAGE8 = Message.builder()
                    .user(USER1)
                    .store(STORE3)
                    .design(DESIGN3)
                    .designType(STORE)
                    .requestDetailType(RequestDetailType.CUSTOM)
                    .requestDetailImageUrl(designImageUrl+"/friend_picture.png")
                    .orderStatus(ACCEPTED)
                    .makerResponse("50000")
                    .responseTime(LocalDateTime.of(2025, 5, 19, 10, 0))
                    .responseStatus(UNRESPONSIVE)
                    .build();

            Message DUMMY_MESSAGE9 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN1)
                    .designType(STORE)
                    .requestDetailType(EVENT)
                    .requestDetailDesign(DESIGN2)
                    .makerResponse("26500")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 18, 11, 0))
                    .responseStatus(CANCELED)
                    .build();

            Message DUMMY_MESSAGE10 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN8)
                    .designType(STORE)
                    .requestDetailType(EVENT)
                    .requestDetailDesign(DESIGN3)
                    .makerResponse("34500")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 20, 13, 0))
                    .responseStatus(CANCELED)
                    .build();

            Message DUMMY_MESSAGE11 = Message.builder()
                    .user(USER1)
                    .store(STORE2)
                    .design(DESIGN2)
                    .designType(STORE)
                    .requestDetailType(EVENT)
                    .requestDetailDesign(DESIGN1)
                    .makerResponse("40000")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 22, 15, 0))
                    .responseStatus(CANCELED)
                    .build();

            Message DUMMY_MESSAGE12 = Message.builder()
                    .user(USER1)
                    .store(STORE3)
                    .design(DESIGN3)
                    .designType(STORE)
                    .requestDetailType(EVENT)
                    .requestDetailDesign(DESIGN8)
                    .makerResponse("55000")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 23, 16, 0))
                    .responseStatus(CANCELED)
                    .build();

            Message DUMMY_MESSAGE13 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN1)
                    .designType(STORE)
                    .requestDetailType(EVENT)
                    .requestDetailDesign(DESIGN2)
                    .makerResponse("30000")
                    .orderStatus(ACCEPTED)
                    .responseTime(LocalDateTime.of(2025, 5, 24, 18, 0))
                    .responseStatus(CANCELED)
                    .build();

            messageList.add(DUMMY_MESSAGE1);
            messageList.add(DUMMY_MESSAGE2);
            messageList.add(DUMMY_MESSAGE3);
            messageList.add(DUMMY_MESSAGE4);
            messageList.add(DUMMY_MESSAGE5);
            messageList.add(DUMMY_MESSAGE6);
            messageList.add(DUMMY_MESSAGE7);
            messageList.add(DUMMY_MESSAGE8);
            messageList.add(DUMMY_MESSAGE9);
            messageList.add(DUMMY_MESSAGE10);
            messageList.add(DUMMY_MESSAGE11);
            messageList.add(DUMMY_MESSAGE12);
            messageList.add(DUMMY_MESSAGE13);

            messageRepository.saveAll(messageList);
        }
    }
}