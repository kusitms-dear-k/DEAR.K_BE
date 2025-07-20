package com.deark.be.design.domain;

import com.deark.be.design.domain.type.OptionCategory;
import com.deark.be.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cake_design_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CakeDesignOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_id", nullable = false)
    private CakeDesign cakeDesign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "option_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private OptionCategory optionCategory;

    @Column(name = "name")
    private String name;

    @Builder
    public CakeDesignOption(CakeDesign cakeDesign, Store store, OptionCategory optionCategory, String name) {
        this.cakeDesign = cakeDesign;
        this.store = store;
        this.optionCategory = optionCategory;
        this.name = name;
    }
}