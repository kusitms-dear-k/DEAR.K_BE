package com.deark.be.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "common_question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommonQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_question_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "hint", nullable = false)
    private String hint;

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired;

    @Builder
    public CommonQuestion(String title, String hint, Boolean isRequired) {
        this.title = title;
        this.hint = hint;
        this.isRequired = isRequired;
    }
}