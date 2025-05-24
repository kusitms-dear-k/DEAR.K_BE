package com.deark.be.event.domain;

import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "event_store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EventStore extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "memo")
    private String memo;

    @Builder
    public EventStore(Event event, Store store, String memo) {
        this.event = event;
        this.store = store;
        this.memo = memo;
    }

    public void assignEvent(Event event) {
        this.event = event;
    }
    public void updateMemo(String memo){
        this.memo=memo;
    }
}