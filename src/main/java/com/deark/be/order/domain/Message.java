package com.deark.be.order.domain;

import com.deark.be.design.domain.Design;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_detail_design_id")
    private Design requestDetailDesign;

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

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QA> qaList = new ArrayList<>();

    @Builder
    public Message(User user, Store store, Design design, Design requestDetailDesign, OrderStatus orderStatus,
                   DesignType designType, String designUrl, RequestDetailType requestDetailType,
                   String requestDetailImageUrl, LocalDateTime responseTime, String makerResponse,
                   ProgressStatus progressStatus, ResponseStatus responseStatus) {
        this.user = user;
        this.store = store;
        this.design = design;
        this.requestDetailDesign = requestDetailDesign;
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
        return this.designType == DesignType.STORE ? design.getName() : "";
    }

    public String getDesignImageUrl() {
        return this.designType == DesignType.STORE ? design.getImageUrl() : designUrl;
    }

    public void addQA(QA qa) {
        qaList.add(qa);
        qa.assignMessage(this);
    }

    public void updateResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}