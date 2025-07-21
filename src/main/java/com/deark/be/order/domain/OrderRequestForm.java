package com.deark.be.order.domain;

import com.deark.be.design.domain.CakeDesign;
import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.order.domain.type.*;
import com.deark.be.store.domain.Store;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "order_request_form")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderRequestForm extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_request_form_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_design_id")
    private CakeDesign cakeDesign;

    @Column(name = "design_url")
    private String designUrl;

    //TODO: 찜하기 CakeDesign 추가 맞는지 확인

    @OneToMany(mappedBy = "orderRequestForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderRequestFormQa> orderRequestFormQaList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "design_type", nullable = false)
    private DesignType designType;

    @Enumerated(EnumType.STRING)
    @Column(name = "make_status")
    private MakeStatus makeStatus;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    @Column(name = "maker_response")
    private String makerResponse;

    @Column(name = "is_store_design")
    private Boolean isStoreDesign;

    @Column(name = "custom_design_image_url")
    private String customDesignImageUrl;

    @Column(name = "additional_request")
    private String additionalRequest;

    @Builder
    public OrderRequestForm(
            User user, Store store, CakeDesign cakeDesign, OrderStatus orderStatus, LocalDateTime responseTime, String designUrl, DesignType designType,
            String makerResponse, MakeStatus makeStatus, Boolean isStoreDesign, String customDesignImageUrl, String additionalRequest
    ) {
        this.user = user;
        this.store = store;
        this.cakeDesign = cakeDesign;
        this.designType = designType;
        this.orderStatus = orderStatus;
        this.responseTime = responseTime;
        this.designUrl = designUrl;
        this.makerResponse = makerResponse;
        this.makeStatus = makeStatus;
        this.isStoreDesign = isStoreDesign;
        this.customDesignImageUrl = customDesignImageUrl;
        this.additionalRequest = additionalRequest;
    }

    public void addQA(OrderRequestFormQa orderRequestFormQa) {
        orderRequestFormQaList.add(orderRequestFormQa);
        orderRequestFormQa.assignMessage(this);
    }
}