package com.deark.be.user.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.user.dto.request.SaveProfileRequest;
import com.deark.be.user.dto.request.UpdateRoleRequest;
import com.deark.be.user.service.UserService;
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

import static com.deark.be.global.dto.ResponseTemplate.EMPTY_RESPONSE;

@Tag(name = "User", description = "유저 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "GUEST 유저 role 저장", description = "로그인 후 유저가 선택한 role을 입력해주세요. <br>" +
            "EX) OWNER 또는 CUSTOMER")
    @PutMapping("/role")
    public ResponseEntity<ResponseTemplate<Object>> socialLogin(
            @AuthenticationPrincipal Long userId,
            @RequestBody UpdateRoleRequest request) {

        userService.updateRole(userId, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EMPTY_RESPONSE);
    }

    @Operation(summary = "닉네임 중복 검사", description = "닉네임이 존재하면 true, 존재하지 않으면 false를 반환합니다")
    @GetMapping("/nickname/validation")
    public ResponseEntity<ResponseTemplate<Object>> validateNickname(
            @Valid @RequestParam String nickname) {

        Boolean response = userService.validateNickname(nickname);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }

    @Operation(summary = "프로필 등록", description = "회원가입 후 프로필 사진, 닉네임, 성별, 생년월일을 등록합니다 <br>" +
            "마케팅 수신 동의 여부는 isMarketingAgreement에, 제3자 제공 동의 여부는 isThirdPartyAgreement에 입력해주세요.")
    @PostMapping(value = "/profile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseTemplate<Object>> saveProfile(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestPart SaveProfileRequest request,
            @RequestPart(required = false) MultipartFile file) {

        userService.saveProfile(userId, request, file);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EMPTY_RESPONSE);
    }
}