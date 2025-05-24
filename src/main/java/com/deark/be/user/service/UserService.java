package com.deark.be.user.service;

import com.deark.be.alarm.repository.AlarmRepository;
import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.global.service.S3Service;
import com.deark.be.user.domain.User;
import com.deark.be.user.dto.request.SaveProfileRequest;
import com.deark.be.user.dto.request.UpdateRoleRequest;
import com.deark.be.user.dto.response.HomeResponse;
import com.deark.be.user.exception.UserException;
import com.deark.be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.deark.be.user.exception.errorcode.UserErrorCode.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;
    private final S3Service s3Service;

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

    public Boolean validateNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public void saveProfile(Long userId, SaveProfileRequest request, MultipartFile file) {
        User user = findUser(userId);
        String profileImageUrl = uploadProfileImage(file);
        user.saveProfile(request, profileImageUrl);
    }

    public HomeResponse getHomeResponse(Long userId) {
        if (userId == 0L) {
            return HomeResponse.of("", false);
        }

        User user = findUser(userId);
        Boolean isAlarm = alarmRepository.existsByUserIdAndIsDeletedFalseAndIsReadFalse(userId);
        return HomeResponse.of(user.getName(), isAlarm);
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    private User saveUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.save(User.from(oAuthInfoResponse));
    }

    private String uploadProfileImage(MultipartFile file) {
        return ObjectUtils.isEmpty(file) ? "" : s3Service.uploadFile(file);
    }
}
