package com.deark.be.user.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.user.dto.request.UpdateRoleRequest;
import com.deark.be.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
