package com.deark.be.user.domain;

import com.deark.be.auth.dto.response.OAuthInfoResponse;
import com.deark.be.event.domain.Event;
import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.user.domain.type.Gender;
import com.deark.be.user.domain.type.Role;
import com.deark.be.user.dto.request.SaveProfileRequest;
import jakarta.persistence.*;

import java.time.LocalDate;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "is_marketing_agreement")
    private Boolean isMarketingAgreement;

    @Column(name = "is_push_agreement")
    private Boolean isThirdPartyAgreement;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> eventList = new ArrayList<>();

    @Builder
    public User(String name, String email, String phone, String socialId, Role role, Boolean isBlacklist,
                String nickname, String profileImageUrl, Gender gender, LocalDate birthDate, List<Event> eventList,
                Boolean isMarketingAgreement, Boolean isThirdPartyAgreement) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.socialId = socialId;
        this.role = role;
        this.isBlacklist = isBlacklist;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.gender = gender;
        this.birthDate = birthDate;
        this.isMarketingAgreement = isMarketingAgreement;
        this.isThirdPartyAgreement = isThirdPartyAgreement;
        this.eventList = eventList;
    }

    public static User from(OAuthInfoResponse oAuthInfoResponse) {
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

    public void saveProfile(SaveProfileRequest request, String profileImageUrl) {
        this.nickname = request.nickname();
        this.gender = request.gender();
        this.birthDate = request.birthDate();
        this.isMarketingAgreement = request.isMarketingAgreement();
        this.isThirdPartyAgreement = request.isThridPartyAgreement();

        if (!profileImageUrl.isEmpty()) {
            this.profileImageUrl = profileImageUrl;
        }
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