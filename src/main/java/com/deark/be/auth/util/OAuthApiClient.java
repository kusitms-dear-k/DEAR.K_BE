package com.deark.be.auth.util;

import com.deark.be.auth.dto.response.OAuthInfoResponse;

public interface OAuthApiClient {
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
