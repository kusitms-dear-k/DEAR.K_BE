package com.deark.be.store.service;

import com.deark.be.design.repository.SizeRepository;
import com.deark.be.event.repository.EventStoreRepository;
import com.deark.be.global.service.S3Service;
import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.domain.type.SortType;
import com.deark.be.store.dto.request.StoreBasicInfoRequest;
import com.deark.be.store.dto.request.StoreRegisterRequest;
import com.deark.be.store.dto.response.BusinessHourResponse;
import com.deark.be.store.dto.response.PickUpHourResponse;
import com.deark.be.store.dto.response.SearchStorePagedResult;
import com.deark.be.store.dto.response.SearchStoreResponseList;
import com.deark.be.store.dto.response.StoreDetailResponse;
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
    private final BusinessHoursService businessHoursService;
    private final SizeRepository sizeRepository;
    private final S3Service s3Service;
    private final EventStoreRepository eventStoreRepository;

    @Transactional
    public Long registerStore(StoreRegisterRequest request, Long userId, MultipartFile businessLicenseFile, MultipartFile businessPermitFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        String businessLicenseUrl = s3Service.uploadFile(businessLicenseFile);
        String businessPermitUrl = s3Service.uploadFile(businessPermitFile);

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

    public SearchStoreResponseList getStoreList(Long userId, Long page, Long count, SortType sortType,
                                                 String keyword, Boolean isSameDayOrder, List<String> locationList,
                                                 LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice, Boolean isLunchBoxCake) {

        SearchStorePagedResult allSearchResult = storeRepository.findAllStoreByCriteria(userId, page, count, sortType,
                keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isLunchBoxCake);

        return SearchStoreResponseList.of(allSearchResult.totalCount(), page, allSearchResult.hasNext(), allSearchResult.storeList());
    }

    public StoreDetailResponse getstoreDetail(Long storeId) {
        List <String> sizeNameList=sizeRepository.findSizeNamesByStoreId(storeId);
        Long likeCount=eventStoreRepository.countByStoreId(storeId);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

        boolean is24hSelfService = Boolean.TRUE.equals(store.getIsSelfService()) &&
                store.getBusinessHoursList().stream()
                        .anyMatch(BusinessHours::getIsOpen24Hours);

        boolean isLunchBoxCake = sizeRepository.existsByStoreIdAndNameContaining(storeId, "도시락");

        List<PickUpHourResponse> pickupHours  = store.getBusinessHoursList().stream()
                .map(PickUpHourResponse::from)
                .toList();

        List<BusinessHourResponse> businessHours=pickupHours.stream()
                .map(p->new BusinessHourResponse(p.dayName(),p.startTime(),p.endTime()))
                .toList();



        return  StoreDetailResponse.from(
                store,
                is24hSelfService,
                isLunchBoxCake,
                businessHours,
                pickupHours,
                likeCount,
                sizeNameList
        );
    }
}
