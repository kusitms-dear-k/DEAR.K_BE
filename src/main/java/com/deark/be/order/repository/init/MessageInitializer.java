package com.deark.be.order.repository.init;

import com.deark.be.design.domain.Design;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.order.domain.Message;
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

import java.util.ArrayList;
import java.util.List;

import static com.deark.be.order.domain.type.DesignType.CUSTOM;
import static com.deark.be.order.domain.type.DesignType.STORE;
import static com.deark.be.order.domain.type.RequestDetailType.EVENT;
import static com.deark.be.order.domain.type.Status.*;

@Slf4j
@RequiredArgsConstructor
@Order(4)
@DummyDataInit
public class MessageInitializer implements ApplicationRunner {

    public static final RequestDetailType REQUEST_DETAIL_TYPE = RequestDetailType.CUSTOM;
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
                    .status(PENDING)
                    .build();

            Message DUMMY_MESSAGE2 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN8)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN3)
                    .requestDetailType(EVENT)
                    .status(ACCEPTED)
                    .makerResponse("안녕하세요 고객님 :) 문의해주셔서 감사합니다. 해당 디자인으로 말씀하신 일정에 가능합니다!")
                    .build();

            Message DUMMY_MESSAGE3 = Message.builder()
                    .user(USER1)
                    .store(STORE1)
                    .design(DESIGN1)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN8)
                    .requestDetailType(EVENT)
                    .status(REJECTED)
                    .makerResponse("디자인에 사용될 재료 부족 및 소진")
                    .build();

            Message DUMMY_MESSAGE4 = Message.builder()
                    .user(USER1)
                    .store(STORE2)
                    .design(DESIGN2)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN8)
                    .requestDetailType(EVENT)
                    .requestDetailDesign(DESIGN1)
                    .status(PENDING)
                    .build();

            Message DUMMY_MESSAGE5 = Message.builder()
                    .user(USER1)
                    .store(STORE2)
                    .design(DESIGN2)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN1)
                    .requestDetailType(EVENT)
                    .status(ACCEPTED)
                    .makerResponse("안녕하세요 고객님 :) 문의해주셔서 감사합니다. 해당 디자인으로 말씀하신 일정에 가능합니다!")
                    .build();

            Message DUMMY_MESSAGE6 = Message.builder()
                    .user(USER1)
                    .store(STORE2)
                    .design(DESIGN2)
                    .designType(STORE)
                    .requestDetailDesign(DESIGN8)
                    .requestDetailType(EVENT)
                    .status(REJECTED)
                    .makerResponse("기간 내 제작 불가")
                    .build();

            Message DUMMY_MESSAGE7 = Message.builder()
                    .user(USER1)
                    .store(STORE3)
                    .designType(CUSTOM)
                    .designUrl(designImageUrl + "/bear.png")
                    .requestDetailType(RequestDetailType.CUSTOM)
                    .requestDetailImageUrl(designImageUrl+"/heart.png")
                    .status(PENDING)
                    .build();

            Message DUMMY_MESSAGE8 = Message.builder()
                    .user(USER1)
                    .store(STORE3)
                    .design(DESIGN3)
                    .designType(STORE)
                    .requestDetailType(RequestDetailType.CUSTOM)
                    .requestDetailImageUrl(designImageUrl+"/friend_picture.png")
                    .status(ACCEPTED)
                    .build();

            messageList.add(DUMMY_MESSAGE1);
            messageList.add(DUMMY_MESSAGE2);
            messageList.add(DUMMY_MESSAGE3);
            messageList.add(DUMMY_MESSAGE4);
            messageList.add(DUMMY_MESSAGE5);
            messageList.add(DUMMY_MESSAGE6);
            messageList.add(DUMMY_MESSAGE7);
            messageList.add(DUMMY_MESSAGE8);

            messageRepository.saveAll(messageList);
        }
    }
}