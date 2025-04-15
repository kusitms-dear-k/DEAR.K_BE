package com.deark.be.event.domain;

import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @Builder
    public Event(User user, String title, String address, LocalDateTime eventDate) {
        this.user = user;
        this.title = title;
        this.address = address;
        this.eventDate = eventDate;
    }
}