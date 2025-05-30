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

@Table(name = "design")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "design", fetch = FetchType.LAZY)
    private List<EventDesign> eventDesignList = new ArrayList<>();

    @OneToMany(mappedBy = "design", fetch = FetchType.LAZY)
    private List<Size> sizeList = new ArrayList<>();

    @Builder
    public Design(Store store, String name, String description, Long price, String imageUrl,
                  List<EventDesign> eventDesignList, List<Size> sizeList) {
        this.store = store;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.eventDesignList = eventDesignList;
        this.sizeList = sizeList;
    }
}