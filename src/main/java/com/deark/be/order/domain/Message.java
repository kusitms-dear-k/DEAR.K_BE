package com.deark.be.order.domain;

import com.deark.be.design.domain.Design;
import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.order.domain.type.DesignType;
import com.deark.be.order.domain.type.Status;
import com.deark.be.store.domain.Store;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "message")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_id")
    private Design design;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "design_type", nullable = false)
    private DesignType designType;

    @Column(name = "design_url")
    private String designUrl;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    @Column(name = "maker_response")
    private String makerResponse;

    @Builder
    public Message(User user, Store store, Design design, Status status, DesignType designType, String designUrl, LocalDateTime responseTime, String makerResponse) {
        this.user = user;
        this.store = store;
        this.design = design;
        this.status = status;
        this.designType = designType;
        this.designUrl = designUrl;
        this.responseTime = responseTime;
        this.makerResponse = makerResponse;
    }

    public String getDesignName() {
        return this.designType == DesignType.STORE ? design.getName() : "";
    }

    public String getDesignImageUrl() {
        return this.designType == DesignType.STORE ? design.getImageUrl() : designUrl;
    }
}