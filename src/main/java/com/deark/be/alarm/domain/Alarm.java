package com.deark.be.alarm.domain;

import com.deark.be.alarm.domain.type.Type;
import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "alarm")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Alarm extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private OrderRequestForm orderRequestForm;

    @Column(name = "content", nullable = false)
    String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Builder
    public Alarm(User user, OrderRequestForm orderRequestForm, String content, Type type, Boolean isRead, Boolean isDeleted) {
        this.user = user;
        this.orderRequestForm = orderRequestForm;
        this.content = content;
        this.type = type;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
    }

    public void markAsDeleted() {
        this.isDeleted = true;
    }

    public void markAsRead() {
        this.isRead = true;
    }
}