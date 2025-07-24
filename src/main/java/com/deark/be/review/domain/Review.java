package com.deark.be.review.domain;

import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.review.domain.type.ReviewType;
import com.deark.be.store.domain.Store;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "review_type", nullable = false)
    private ReviewType reviewType;

    @Column(name = "is_gifted", nullable = false)
    private Boolean isGifted;

    @Builder
    public Review(Store store, User user, ReviewType reviewType, Boolean isGifted) {
        this.store = store;
        this.user = user;
        this.reviewType = reviewType;
        this.isGifted = isGifted;
    }
}