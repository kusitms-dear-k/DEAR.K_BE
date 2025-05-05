package com.deark.be.user.domain;

import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.event.domain.Event;
import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.user.domain.type.Role;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import static com.deark.be.user.domain.type.Role.GUEST;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_blacklist", nullable = false)
    private Boolean isBlacklist;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> eventList=new ArrayList<>();

    @Builder
    public User(String name, String email, String phone, String socialId, Role role, Boolean isBlacklist,
                String nickname, String profileImageUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.socialId = socialId;
        this.role = role;
        this.isBlacklist = isBlacklist;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public static User of(OAuthInfoResponse oAuthInfoResponse) {
        return User.builder()
                .name(oAuthInfoResponse.getName())
                .email(oAuthInfoResponse.getEmail())
                .phone(formatPhoneNumber(oAuthInfoResponse.getPhoneNumber()))
                .socialId(oAuthInfoResponse.getId())
                .role(GUEST)
                .isBlacklist(false)
                .nickname(oAuthInfoResponse.getNickname())
                .profileImageUrl(oAuthInfoResponse.getProfileImage())
                .build();
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    private static String formatPhoneNumber(String originalPhoneNumber) {
        String formattedNumber = originalPhoneNumber;

        if (!StringUtils.hasText(originalPhoneNumber)) {
            formattedNumber = "";
        } else if (originalPhoneNumber.startsWith("+82 ")) {
            formattedNumber = originalPhoneNumber.replace("+82 ", "0");
        }

        return formattedNumber;
    }
}