package com.deark.be.order.domain;

import com.deark.be.order.domain.type.QuestionType;
import com.deark.be.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "order_question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @Column(name = "content")
    private String content;

    @Column(name = "is_required")
    private Boolean isRequired;

    @Builder
    public OrderQuestion(Store store, QuestionType questionType, String content, Boolean isRequired) {
        this.store = store;
        this.questionType = questionType;
        this.content = content;
        this.isRequired = isRequired;
    }
}