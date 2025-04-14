package com.deark.be.alarm.domain;

import com.deark.be.alarm.domain.type.Type;
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
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Builder
    public Alarm(User user, String content, Type type, Boolean isRead) {
        this.user = user;
        this.content = content;
        this.type = type;
        this.isRead = isRead;
    }
}