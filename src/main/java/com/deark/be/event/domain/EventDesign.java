package com.deark.be.event.domain;

import com.deark.be.design.domain.Design;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "event_design")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EventDesign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_design_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_id", nullable = false)
    private Design design;

    @Builder
    public EventDesign(Event event, Design design) {
        this.event = event;
        this.design = design;
    }
}