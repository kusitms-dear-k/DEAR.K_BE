package com.deark.be.store.domain;

import com.deark.be.store.domain.type.BusinessDay;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

    @Column(name = "name", nullable = false)
    private BusinessDay businessDay;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "daily_reservation_limit")
    private Long dailyReservationLimit;

    @Builder
    public BusinessHours(Store store, BusinessDay businessDay, LocalTime openTime, LocalTime closeTime,
                         Long dailyReservationLimit) {
        this.store = store;
        this.businessDay = businessDay;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.dailyReservationLimit = dailyReservationLimit;
    }
}

