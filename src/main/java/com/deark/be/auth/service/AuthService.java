package com.deark.be.auth.service;

import com.deark.be.auth.dto.request.OAuthLoginRequest;
import com.deark.be.auth.dto.response.OAuthInfoResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final RequestOAuthInfoService requestOAuthInfoService;

    public OAuthInfoResponse socialLogin(OAuthLoginRequest request, HttpServletResponse response) {
        return requestOAuthInfoService.request(request);
    }
}
