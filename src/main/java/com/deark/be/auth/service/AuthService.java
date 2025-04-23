package com.deark.be.auth.service;

import com.deark.be.auth.dto.request.OAuthLoginRequest;
import com.deark.be.auth.dto.response.LoginResponse;
import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.auth.util.JwtTokenProvider;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public LoginResponse socialLogin(OAuthLoginRequest request, HttpServletResponse response) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(request);
        User user = userService.findOrGenerateUser(oAuthInfoResponse);
        return getLoginResponse(response, user);
    }

    // JWT 토큰 발급 및 AccessToken, RefreshToken 반환
    private LoginResponse getLoginResponse(HttpServletResponse response, User user) {
        String accessToken = jwtTokenProvider.createAccessToken(user);
        jwtTokenProvider.createRefreshToken(user, response);
        response.setHeader("Authorization", "Bearer " + accessToken);

        return LoginResponse.from(user);
    }
}
