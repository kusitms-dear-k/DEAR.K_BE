package com.deark.be.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "qa")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class QA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qa_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "is_required")
    private Boolean isRequired;

    public void assignMessage(Message message) {
        this.message = message;
    }

    @Builder
    public QA(Message message, String question, String answer, Boolean isRequired) {
        this.message = message;
        this.question = question;
        this.answer = answer;
        this.isRequired = isRequired;
    }
}