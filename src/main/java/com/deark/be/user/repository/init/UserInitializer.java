package com.deark.be.user.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

import static com.deark.be.user.domain.type.Role.*;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class UserInitializer implements ApplicationRunner {

    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            log.info("[User] 더미 데이터 존재");
        } else {
            List<User> userList = new ArrayList<>();

            User DUMMY_ADMIN = User.builder()
                    .name("관리자")
                    .email("admin@email.com")
                    .phone("010-1111-1111")
                    .socialId("adminSocialId")
                    .role(ADMIN)
                    .isBlacklist(false)
                    .build();

            User DUMMY_USER1 = User.builder()
                    .name("오케이")
                    .email("5cake@email.com")
                    .phone("010-4276-8462")
                    .socialId("5cakeSocialId")
                    .role(CUSTOMER)
                    .isBlacklist(false)
                    .build();

            User DUMMY_USER2 = User.builder()
                    .name("사장이")
                    .email("owner@email.com")
                    .phone("010-8459-1256")
                    .socialId("ownerSocialId")
                    .role(OWNER)
                    .isBlacklist(false)
                    .build();

            userList.add(DUMMY_USER1);
            userList.add(DUMMY_USER2);
            userList.add(DUMMY_ADMIN);

            userRepository.saveAll(userList);
        }
    }
}