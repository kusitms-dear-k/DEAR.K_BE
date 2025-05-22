package com.deark.be.user.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.user.domain.Bank;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.BankRepository;
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
@Order(2)
@DummyDataInit
public class UserInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            log.info("[User] 더미 데이터 존재");
        } else {
            Bank BANK1 = bankRepository.findById(1L).orElseThrow();
            Bank BANK2 = bankRepository.findById(2L).orElseThrow();
            Bank BANK3 = bankRepository.findById(3L).orElseThrow();
            Bank BANK4 = bankRepository.findById(4L).orElseThrow();
            Bank BANK5 = bankRepository.findById(5L).orElseThrow();

            List<User> userList = new ArrayList<>();

            // 기존 관리자, 고객, 사장님 1명
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

            User DUMMY_OWNER0 = User.builder()
                    .name("사장이")
                    .email("owner@email.com")
                    .phone("010-8459-1256")
                    .socialId("ownerSocialId")
                    .role(OWNER)
                    .isBlacklist(false)
                    .bank(BANK1)
                    .account("123-456-789012")
                    .build();

            User DUMMY_OWNER1 = User.builder()
                    .name("김대표")
                    .email("kim.owner1@email.com")
                    .phone("010-2345-6781")
                    .socialId("owner1SocialId")
                    .role(OWNER)
                    .isBlacklist(false)
                    .bank(BANK2)
                    .account("234-567-890123")
                    .build();

            User DUMMY_OWNER2 = User.builder()
                    .name("이대표")
                    .email("lee.owner2@email.com")
                    .phone("010-3456-7892")
                    .socialId("owner2SocialId")
                    .role(OWNER)
                    .isBlacklist(false)
                    .bank(BANK3)
                    .account("345-678-901234")
                    .build();

            User DUMMY_OWNER3 = User.builder()
                    .name("박대표")
                    .email("park.owner3@email.com")
                    .phone("010-4567-8912")
                    .socialId("owner3SocialId")
                    .role(OWNER)
                    .isBlacklist(false)
                    .bank(BANK4)
                    .account("456-789-012345")
                    .build();

            User DUMMY_OWNER4 = User.builder()
                    .name("최대표")
                    .email("choi.owner4@email.com")
                    .phone("010-5678-9123")
                    .socialId("owner4SocialId")
                    .role(OWNER)
                    .isBlacklist(false)
                    .bank(BANK5)
                    .account("567-890-123456")
                    .build();

            User DUMMY_OWNER5 = User.builder()
                    .name("정대표")
                    .email("jung.owner5@email.com")
                    .phone("010-6789-1234")
                    .socialId("owner5SocialId")
                    .role(OWNER)
                    .isBlacklist(false)
                    .bank(BANK1)
                    .account("678-901-234567")
                    .build();

            userList.add(DUMMY_USER1);
            userList.add(DUMMY_OWNER0);
            userList.add(DUMMY_OWNER1);
            userList.add(DUMMY_OWNER2);
            userList.add(DUMMY_OWNER3);
            userList.add(DUMMY_OWNER4);
            userList.add(DUMMY_OWNER5);
            userList.add(DUMMY_ADMIN);

            userRepository.saveAll(userList);
        }
    }
}
