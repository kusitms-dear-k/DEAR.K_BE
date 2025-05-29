package com.deark.be.auth.util;

import com.deark.be.auth.service.type.JwtUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationUtil {

    private final JwtTokenProvider jwtTokenProvider;

    public void setAuthenticationFromRequest(HttpServletRequest request, String token) {
        Authentication authentication = makeAuthentication(request, token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserAuthentication makeAuthentication(HttpServletRequest request, String token) {
        UserAuthentication authentication = UserAuthentication.from(jwtTokenProvider.getJwtUserDetails(token));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    public void setDummyAuthentication(HttpServletRequest request) {
        UserAuthentication authentication = UserAuthentication.from(JwtUserDetails.DUMMY_USER_DETAILS);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}