package com.deark.be.store.controller;

import static com.deark.be.global.dto.ResponseTemplate.EMPTY_RESPONSE;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.store.dto.request.StoreBasicInfoRequest;
import com.deark.be.store.dto.request.StoreRegisterRequest;
import com.deark.be.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Store", description = "가게 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "가게 입점 등록", description = """
        S3에 미리 업로드된 파일 URL을 기반으로 가게 입점 정보를 등록합니다.
        """)
    public ResponseEntity<ResponseTemplate<Object>> registerStore(
            @RequestPart("request") @Valid StoreRegisterRequest request,
            @RequestPart("businessLicenseFile") MultipartFile businessLicenseFile,
            @RequestPart("businessPermitFile") MultipartFile businessPermitFile,
            @AuthenticationPrincipal Long userId
    ) {
        Long storeId = storeService.registerStore(request,userId,businessLicenseFile,businessPermitFile);
        return ResponseEntity
                .ok(ResponseTemplate.from(storeId));
    }

    @PostMapping("/basic-info")
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
}
