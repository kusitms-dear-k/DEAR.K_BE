package com.deark.be.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "bank")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;

    @Column(name = "bank_name", nullable = false, unique = true)
    private String name;

    @Column(name = "bank_image_url", nullable = false)
    private String imageUrl;

    @Builder
    public Bank(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}