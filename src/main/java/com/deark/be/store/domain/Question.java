package com.deark.be.store.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_form_id", nullable = false)
    private OrderForm orderForm;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Question(OrderForm orderForm, String content) {
        this.orderForm = orderForm;
        this.content = content;
    }
}