package com.deark.be.design.domain;

import com.deark.be.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "sheet")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Sheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sheet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_id", nullable = false)
    private Design design;

    @Column(name = "name")
    private String name;

    @Builder
    public Sheet(Store store, Design design, String name) {
        this.store = store;
        this.design = design;
        this.name = name;
    }
}