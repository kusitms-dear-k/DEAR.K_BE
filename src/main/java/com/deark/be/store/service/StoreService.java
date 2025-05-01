package com.deark.be.store.service;

import static com.deark.be.store.exception.errorcode.StoreErrorCode.STORE_NOT_FOUND;
import static com.deark.be.user.exception.errorcode.UserErrorCode.USER_NOT_FOUND;

import com.deark.be.global.service.S3Service;
import com.deark.be.store.domain.Store;
import com.deark.be.store.dto.request.StoreBasicInfoRequest;
import com.deark.be.store.dto.request.StoreRegisterRequest;
import com.deark.be.store.exception.StoreException;
import com.deark.be.store.repository.StoreRepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.exception.UserException;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final BusinessHoursService businessHoursService;
    private final S3Service s3Service;


    @Transactional
    public Long registerStore(StoreRegisterRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        String businessLicenseUrl = s3Service.uploadFile(request.businessLicenseFile());
        String businessPermitUrl = s3Service.uploadFile(request.businessPermitFile());

        Store store = request.toEntity(user, businessLicenseUrl, businessPermitUrl);
        return storeRepository.save(store).getId();
    }

    @Transactional
    public void registerStoreBasicInfo(Long storeId, StoreBasicInfoRequest request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        request.updateBasicInfo(store);
        businessHoursService.registerBusinessHours(store, request.businessHours());
    }
}
