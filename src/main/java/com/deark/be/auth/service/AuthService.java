package com.deark.be.auth.service;

import com.deark.be.auth.dto.request.OAuthLoginRequest;
import com.deark.be.auth.dto.response.LoginResponse;
import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.auth.dto.response.ReissueResponse;
import com.deark.be.auth.util.JwtTokenProvider;
import com.deark.be.global.exception.GlobalException;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.deark.be.global.exception.errorcode.GlobalErrorCode.INVALID_TOKEN;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public String getTestToken(Long userId) {
        User user = userService.findUser(userId);
        return jwtTokenProvider.createAccessToken(user);
    }

    // AccessToken 재발급
    public ReissueResponse reIssueToken(String refreshToken) {
        refreshToken = resolveToken(refreshToken);

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new GlobalException(INVALID_TOKEN);
        }

        User user = jwtTokenProvider.getUser(refreshToken);
        String accessToken = jwtTokenProvider.createAccessToken(user);

        return ReissueResponse.from(accessToken);
    }

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

    private String resolveToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        throw new GlobalException(INVALID_TOKEN);
    }
}
