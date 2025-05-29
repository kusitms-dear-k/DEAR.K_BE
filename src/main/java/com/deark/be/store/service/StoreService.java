package com.deark.be.store.service;

import com.deark.be.design.domain.Size;
import com.deark.be.design.repository.SizeRepository;
import com.deark.be.event.repository.EventStoreRepository;
import com.deark.be.global.service.S3Service;
import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.domain.type.SortType;
import com.deark.be.store.dto.request.StoreBasicInfoRequest;
import com.deark.be.store.dto.request.StoreRegisterRequest;
import com.deark.be.store.dto.response.*;
import com.deark.be.store.exception.StoreException;
import com.deark.be.store.repository.StoreRepository;
import com.deark.be.user.domain.User;
import com.deark.be.user.exception.UserException;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static com.deark.be.store.exception.errorcode.StoreErrorCode.STORE_NOT_FOUND;
import static com.deark.be.user.exception.errorcode.UserErrorCode.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final SizeRepository sizeRepository;
    private final EventStoreRepository eventStoreRepository;

    private final S3Service s3Service;
    private final BusinessHoursService businessHoursService;

    @Transactional
    public Long registerStore(StoreRegisterRequest request, Long userId, MultipartFile businessLicenseFile,
                              MultipartFile businessPermitFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        String businessLicenseUrl = s3Service.uploadFile(businessLicenseFile);
        String businessPermitUrl = s3Service.uploadFile(businessPermitFile);

        Store store = request.toEntity(user, businessLicenseUrl, businessPermitUrl);
        return storeRepository.save(store).getId();
    }

    @Transactional
    public void registerStoreBasicInfo(Long storeId, StoreBasicInfoRequest request) {
        Store store = getStoreByIdOrThrow(storeId);

        request.updateBasicInfo(store);
        businessHoursService.registerBusinessHours(store, request.businessHours());
    }

    public SearchStoreResponseList getStoreList(Long userId, Long page, Long count, SortType sortType,
                                                String keyword, Boolean isSameDayOrder, List<String> locationList,
                                                LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice,
                                                Boolean isSelfService, Boolean isLunchBoxCake) {

        SearchStorePagedResult allSearchResult = storeRepository.findAllStoreByCriteria(userId, page, count, sortType,
                keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isSelfService, isLunchBoxCake);

        return SearchStoreResponseList.of(allSearchResult.totalCount(), page, allSearchResult.hasNext(),
                allSearchResult.storeList());
    }

    public StoreDetailResponse getStoreDetail(Long storeId, Long userId) {
        Store store = getStoreByIdOrThrow(storeId);

        List<String> sizeNameList = store.getSizeList().stream()
                .map(Size::getName)
                .distinct()
                .toList();

        Long likeCount = eventStoreRepository.countByStoreId(storeId);

        boolean is24hSelfService = Boolean.TRUE.equals(store.getIsSelfService()) &&
                store.getBusinessHoursList().stream()
                        .anyMatch(BusinessHours::getIsOpen24Hours);

        boolean isLunchBoxCake = sizeRepository.existsByStoreIdAndNameContaining(storeId, "도시락");
        boolean isLiked = eventStoreRepository.existsByEventUserIdAndStoreId(userId, storeId);
        List<PickUpHourResponse> pickupHours = store.getBusinessHoursList().stream()
                .map(PickUpHourResponse::from)
                .toList();

        List<BusinessHourResponse> businessHours = pickupHours.stream()
                .map(p -> new BusinessHourResponse(p.dayName(), p.startTime(), p.endTime()))
                .toList();

        return StoreDetailResponse.of(
                store,
                is24hSelfService,
                isLunchBoxCake,
                isLiked,
                businessHours,
                pickupHours,
                likeCount,
                sizeNameList
        );
    }

    public Store getStoreByIdOrThrow(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
    }
}
