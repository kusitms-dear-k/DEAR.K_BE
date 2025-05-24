package com.deark.be.user.repository.init;

import com.deark.be.global.util.DummyDataInit;
import com.deark.be.user.domain.Bank;
import com.deark.be.user.repository.BankRepository;
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
@Order(1)
@DummyDataInit
public class BankInitializer implements ApplicationRunner {

    private final BankRepository bankRepository;

    @Value("${spring.cloud.aws.s3.url}/bank")
    private String bankImageUrl;

    @Override
    public void run(ApplicationArguments args) {
        if (bankRepository.count() > 0) {
            log.info("[Bank] 더미 데이터 존재");
        } else {
            List<Bank> bankList = new ArrayList<>();

            Bank DUMMY_BANK1 = Bank.builder()
                    .name("국민은행")
                    .imageUrl(bankImageUrl + "/kb.png")
                    .build();

            Bank DUMMY_BANK2 = Bank.builder()
                    .name("신한은행")
                    .imageUrl(bankImageUrl + "/sinhan.png")
                    .build();

            Bank DUMMY_BANK3 = Bank.builder()
                    .name("우리은행")
                    .imageUrl(bankImageUrl + "/woori.png")
                    .build();

            Bank DUMMY_BANK4 = Bank.builder()
                    .name("하나은행")
                    .imageUrl(bankImageUrl + "/hana.png")
                    .build();

            Bank DUMMY_BANK5 = Bank.builder()
                    .name("기업은행")
                    .imageUrl(bankImageUrl + "/ibk.png")
                    .build();

            bankList.add(DUMMY_BANK1);
            bankList.add(DUMMY_BANK2);
            bankList.add(DUMMY_BANK3);
            bankList.add(DUMMY_BANK4);
            bankList.add(DUMMY_BANK5);

            bankRepository.saveAll(bankList);
        }
    }
}
