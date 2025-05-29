package com.deark.be.auth.service;

import com.deark.be.auth.dto.request.OAuthLoginRequest;
import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.auth.util.OAuthApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestOAuthInfoService {

    private final OAuthApiClient client;

    public OAuthInfoResponse request(OAuthLoginRequest request) {
        return client.requestOauthInfo(request.accessToken());
    }
}
