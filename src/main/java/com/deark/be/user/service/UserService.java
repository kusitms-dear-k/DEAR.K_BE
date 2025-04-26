package com.deark.be.user.service;

import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.user.domain.User;
import com.deark.be.user.dto.request.UpdateRoleRequest;
import com.deark.be.user.exception.UserException;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.deark.be.user.exception.errorcode.UserErrorCode.USER_NOT_FOUND;

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

    @Transactional
    public void updateRole(Long userId, UpdateRoleRequest request) {
        User user = findUser(userId);
        user.updateRole(request.role());
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    private User saveUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.save(User.of(oAuthInfoResponse));
    }
}
