package com.deark.be.user.service;

import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.user.domain.User;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User findOrGenerateUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findBySocialId(oAuthInfoResponse.getId())
                .orElseGet(() -> saveUser(oAuthInfoResponse));
    }

    private User saveUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.save(User.of(oAuthInfoResponse));
    }
}
