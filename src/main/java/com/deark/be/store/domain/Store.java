package com.deark.be.store.domain;

import com.deark.be.design.domain.Cream;
import com.deark.be.design.domain.Design;
import com.deark.be.design.domain.Sheet;
import com.deark.be.design.domain.Size;
import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name")
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

    @Column(name = "is_same_day_order")
    private Boolean isSameDayOrder;

    @Column(name = "business_license_url")
    private String businessLicenseUrl;

    @Column(name = "business_permit_url")
    private String businessPermitUrl;

    @Column(name = "settlement_account")
    private String settlementAccount;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "order_link")
    private String orderLink;

    @Column(name = "max_daily_orders")
    private Integer maxDailyOrders;

    @Column(name = "is_self_service")
    private Boolean isSelfService;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusinessHours> businessHoursList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Size> sizeList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cream> creamList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sheet> sheetList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Design> designList = new ArrayList<>();

    @Builder
    public Store(User user, String name, String description, String phone, String address,
                 String businessNumber, LocalDate establishDate, String imageUrl,
                 Long averageResponseTime, String chattingUrl, Boolean isSameDayOrder,
                 String settlementAccount, String businessLicenseUrl, String businessPermitUrl, String ownerName,
                 String orderLink, Integer maxDailyOrders, Boolean isSelfService, List<BusinessHours> businessHoursList,
                 List<Size> sizeList, List<Cream> creamList, List<Sheet> sheetList, List<Design> designList) {
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
        this.isSameDayOrder = isSameDayOrder;
        this.settlementAccount = settlementAccount;
        this.businessLicenseUrl = businessLicenseUrl;
        this.businessPermitUrl = businessPermitUrl;
        this.ownerName = ownerName;
        this.orderLink = orderLink;
        this.maxDailyOrders = maxDailyOrders;
        this.isSelfService = isSelfService;
        this.businessHoursList = businessHoursList;
        this.sizeList = sizeList;
        this.creamList = creamList;
        this.sheetList = sheetList;
        this.designList = designList;
    }

    // Store 엔티티 내에 추가할 메서드
    public void updateBasicInfo(String name, String phone, String description, String address,
                                String imageUrl, String chattingUrl, Boolean isSelfService,
                                String orderLink, Integer maxDailyOrders) {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.address = address;
        this.imageUrl = imageUrl;
        this.chattingUrl = chattingUrl;
        this.isSelfService = isSelfService;
        this.orderLink = orderLink;
        this.maxDailyOrders = maxDailyOrders;
    }

    public void addBusinessHour(BusinessHours businessHour) {
        businessHoursList.add(businessHour);
        businessHour.assignStore(this); // 역방향 연결
    }

    public void clearBusinessHours() {
        for (BusinessHours bh : businessHoursList) {
            bh.assignStore(null); // 역방향 해제
        }

        businessHoursList.clear();
    }
}

