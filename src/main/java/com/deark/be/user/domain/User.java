package com.deark.be.user.domain;

import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.user.domain.type.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_blacklist", nullable = false)
    private Boolean isBlacklist;

    @Builder
    public User(String name, String email, String phone, String socialId, Role role, Boolean isBlacklist) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.socialId = socialId;
        this.role = role;
        this.isBlacklist = isBlacklist;
    }
}

