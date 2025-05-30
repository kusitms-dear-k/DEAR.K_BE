package com.deark.be.store.domain;

import com.deark.be.store.domain.type.BusinessDay;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Table(name = "business_hours")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BusinessHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_hours_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private BusinessDay businessDay;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "daily_reservation_limit")
    private Long dailyReservationLimit;

    @Column(name = "is_open_24_hours", nullable = false)
    private Boolean isOpen24Hours;

    @Builder
    public BusinessHours(Store store, BusinessDay businessDay, LocalTime openTime, LocalTime closeTime,
                         Long dailyReservationLimit, Boolean isOpen24Hours) {
        this.store = store;
        this.businessDay = businessDay;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.dailyReservationLimit = dailyReservationLimit;
        this.isOpen24Hours = isOpen24Hours;
    }

    public void assignStore(Store store) {
        this.store = store;
    }
}

