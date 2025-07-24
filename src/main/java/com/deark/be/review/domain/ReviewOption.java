package com.deark.be.review.domain;

import com.deark.be.review.domain.type.ReviewOptionCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "review_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(name = "review_option_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewOptionCategory reviewOptionCategory;

    @Column(name = "value", length = 1023)
    private String value;

    @Builder
    public ReviewOption(Review review, ReviewOptionCategory reviewOptionCategory, String value) {
        this.review = review;
        this.reviewOptionCategory = reviewOptionCategory;
        this.value = value;
    }
}