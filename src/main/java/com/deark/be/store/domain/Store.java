package com.deark.be.store.domain;

import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "business_number", nullable = false)
    private String businessNumber;

    @Column(name = "establish_date", nullable = false)
    private LocalDate establishDate;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "average_response_time")
    private Long averageResponseTime;

    @Column(name = "chatting_url")
    private String chattingUrl;

    @Column(name = "is_unmanned", nullable = false)
    private Boolean isUnmanned;

    @Builder
    public Store(User user, String name, String description, String phone, String address,
                 String businessNumber, LocalDate establishDate, String imageUrl,
                 Long averageResponseTime, String chattingUrl, Boolean isUnmanned) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.businessNumber = businessNumber;
        this.establishDate = establishDate;
        this.imageUrl = imageUrl;
        this.averageResponseTime = averageResponseTime;
        this.chattingUrl = chattingUrl;
        this.isUnmanned = isUnmanned;
    }
}

