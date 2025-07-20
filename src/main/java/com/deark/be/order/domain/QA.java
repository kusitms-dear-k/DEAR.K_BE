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
    @JoinColumn(name = "order_request_form_id", nullable = false)
    private OrderRequestForm orderRequestForm;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "is_required")
    private Boolean isRequired;

    public void assignMessage(OrderRequestForm orderRequestForm) {
        this.orderRequestForm = orderRequestForm;
    }

    @Builder
    public QA(OrderRequestForm orderRequestForm, String question, String answer, Boolean isRequired) {
        this.orderRequestForm = orderRequestForm;
        this.question = question;
        this.answer = answer;
        this.isRequired = isRequired;
    }
}