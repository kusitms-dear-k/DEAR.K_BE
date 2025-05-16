package com.deark.be.design.domain;

import com.deark.be.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Table(name = "cream")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cream_id")
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
    public Cream(Store store, Design design, String name) {
        this.store = store;
        this.design = design;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cream cream)) return false;
        return Objects.equals(name, cream.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}