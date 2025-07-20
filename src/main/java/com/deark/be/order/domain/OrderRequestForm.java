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
    private CakeDesign cakeDesign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_detail_design_id")
    private CakeDesign requestDetailCakeDesign;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private ProgressStatus progressStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "response_status", nullable = false)
    private ResponseStatus responseStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "design_type", nullable = false)
    private DesignType designType;

    @Column(name = "design_url")
    private String designUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_detail_type", nullable = false)
    private RequestDetailType requestDetailType;

    @Column(name = "request_detail_url")
    private String requestDetailImageUrl;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    @Column(name = "maker_response")
    private String makerResponse;

    @OneToMany(mappedBy = "orderRequestForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QA> qaList = new ArrayList<>();

    @Builder
    public OrderRequestForm(User user, Store store, CakeDesign cakeDesign, CakeDesign requestDetailCakeDesign, OrderStatus orderStatus,
                            DesignType designType, String designUrl, RequestDetailType requestDetailType,
                            String requestDetailImageUrl, LocalDateTime responseTime, String makerResponse,
                            ProgressStatus progressStatus, ResponseStatus responseStatus) {
        this.user = user;
        this.store = store;
        this.cakeDesign = cakeDesign;
        this.requestDetailCakeDesign = requestDetailCakeDesign;
        this.orderStatus = orderStatus;
        this.designType = designType;
        this.designUrl = designUrl;
        this.requestDetailType = requestDetailType;
        this.requestDetailImageUrl = requestDetailImageUrl;
        this.responseTime = responseTime;
        this.makerResponse = makerResponse;
        this.progressStatus = progressStatus;
        this.responseStatus = responseStatus;
    }

    public String getDesignName() {
        return this.designType == DesignType.STORE ? cakeDesign.getName() : "";
    }

    public String getDesignImageUrl() {
        return this.designType == DesignType.STORE ? cakeDesign.getImageUrl() : designUrl;
    }

    public void addQA(QA qa) {
        qaList.add(qa);
        qa.assignMessage(this);
    }

    public void updateResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}