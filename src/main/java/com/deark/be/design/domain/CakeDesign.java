package com.deark.be.design.domain;

import com.deark.be.event.domain.EventDesign;
import com.deark.be.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "cake_design")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CakeDesign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "cakeDesign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventDesign> eventDesignList = new ArrayList<>();

    @OneToMany(mappedBy = "cakeDesign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CakeDesignOption> cakeDesignOptionList = new ArrayList<>();

    @Column(name = "is_fixed")
    private Boolean isFixed;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public CakeDesign(
            Store store, Boolean isFixed, String name, String description, Long price, String imageUrl
    ) {
        this.store = store;
        this.isFixed = isFixed;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}