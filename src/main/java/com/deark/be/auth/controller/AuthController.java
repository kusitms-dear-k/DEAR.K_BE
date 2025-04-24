package com.deark.be.auth.controller;

import com.deark.be.auth.dto.request.OAuthLoginRequest;
import com.deark.be.auth.dto.response.LoginResponse;
import com.deark.be.auth.service.AuthService;
import com.deark.be.global.dto.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "테스트용 토큰발급", description = "테스트용 토큰발급")
    @GetMapping("/test/{userId}")
    public String testToken(@PathVariable Long userId) {
        return authService.getTestToken(userId);
    }

    @Operation(summary = "카카오 로그인 / 회원가입", description = "카카오 로그인 및 회원가입<br>" +
            "사용자가 로그인 연동 후 받게 되는 Access Token을 넣어주세요. <br><br>" +
            "존재하지 않는 유저라면 GUEST, 존재하는 유저라면 OWNER / CUSTOMER를 반환합니다.<br>" +
            "Access Token은 헤더로, Refresh Token은 쿠키로 전달됩니다.<br>")
    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<Object>> socialLogin(
            @RequestBody OAuthLoginRequest request,
            HttpServletResponse response) {

        LoginResponse loginResponse = authService.socialLogin(request, response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(loginResponse));
    }
}
