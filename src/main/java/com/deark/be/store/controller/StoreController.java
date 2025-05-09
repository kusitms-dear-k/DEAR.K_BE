package com.deark.be.store.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.store.domain.type.SortType;
import com.deark.be.store.dto.request.StoreBasicInfoRequest;
import com.deark.be.store.dto.request.StoreRegisterRequest;
import com.deark.be.store.dto.response.SearchStoreResponseList;
import com.deark.be.store.dto.response.StoreDetailResponse;
import com.deark.be.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static com.deark.be.global.dto.ResponseTemplate.EMPTY_RESPONSE;

@Tag(name = "Store", description = "가게 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "가게 입점 등록", description = """
            S3에 미리 업로드된 파일 URL을 기반으로 가게 입점 정보를 등록합니다.
            """)
    public ResponseEntity<ResponseTemplate<Object>> registerStore(
            @RequestPart("request") @Valid StoreRegisterRequest request,
            @RequestPart("businessLicenseFile") MultipartFile businessLicenseFile,
            @RequestPart("businessPermitFile") MultipartFile businessPermitFile,
            @AuthenticationPrincipal Long userId
    ) {
        Long storeId = storeService.registerStore(request, userId, businessLicenseFile, businessPermitFile);
        return ResponseEntity
                .ok(ResponseTemplate.from(storeId));
    }

    @PostMapping("/basic_info")
    @Operation(summary = "가게 기본 정보 등록", description = "입점 후, 기본정보 및 영업시간을 등록합니다.")
    public ResponseEntity<ResponseTemplate<Object>> registerStoreBasicInfo(
            @RequestParam Long storeId,
            @RequestBody StoreBasicInfoRequest request
    ) {
        storeService.registerStoreBasicInfo(storeId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EMPTY_RESPONSE);
    }

    @Operation(summary = "가게 통합 검색", description = "page는 0부터 시작합니다. hasNext가 false이면 마지막 페이지입니다.<br><br>" +
            "입력받은 값 : keyword / 당일 주문 여부 : isSameDayOrder / 지역 리스트 : locationList (ex. 강남구, 중랑구) <br>" +
            "시작일 : '2025-01-01' 형식으로 startDate / 종료일 : '2025-01-01' 형식으로 endDate (하루만 선택할 경우 시작일과 종료일을 같게 입력해주세요.) <br>"
            +
            "최소 금액 : minPrice / 최대 금액 : maxPrice / 도시락 케이크 여부는 isLunchBoxCake 에 입력해주세요.")
    @GetMapping("/search")
    public ResponseEntity<ResponseTemplate<Object>> searchStore(
            @AuthenticationPrincipal Long userId,
            @RequestParam(defaultValue = "0") Long page,
            @RequestParam(defaultValue = "6") Long count,
            @RequestParam(defaultValue = "ACCURACY") SortType sortType,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isSameDayOrder,
            @RequestParam(required = false) List<String> locationList,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Boolean isLunchBoxCake) {

        SearchStoreResponseList storeList = storeService.getStoreList(
                userId, page, count, sortType,
                keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isLunchBoxCake);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(storeList));
    }

    @Operation(summary = "스토어 상세 조회", description = "스토어 상세 정보를 조회합니다.<br>"
            + "가게에서 설정한 모든 사이즈 정보도 이때 함께 응답합니다.")
    @GetMapping("/detail/{storeId}")
    public ResponseEntity<ResponseTemplate<Object>> getStoreDetail(
            @PathVariable Long storeId
    ) {
        StoreDetailResponse storeDetail = storeService.getstoreDetail(storeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(storeDetail));
    }
}

