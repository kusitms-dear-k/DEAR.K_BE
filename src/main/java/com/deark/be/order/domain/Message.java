package com.deark.be.order.domain;

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
public class Message {

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

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    @Column(name = "maker_response")
    private String makerResponse;

    @Builder
    public Message(User user, Store store, Status status, LocalDateTime responseTime, String makerResponse) {
        this.user = user;
        this.store = store;
        this.status = status;
        this.responseTime = responseTime;
        this.makerResponse = makerResponse;
    }
}