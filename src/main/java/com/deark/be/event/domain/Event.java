package com.deark.be.event.domain;

import com.deark.be.global.domain.BaseTimeEntity;
import com.deark.be.store.domain.BusinessHours;
import com.deark.be.user.domain.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventStore> eventStoreList=new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventDesign> eventDesignList=new ArrayList<>();

    @Builder
    public Event(User user, String title, String address, LocalDate eventDate) {
        this.user = user;
        this.title = title;
        this.address = address;
        this.eventDate = eventDate;
    }

    public void addEventStore(EventStore eventStore) {
        eventStoreList.add(eventStore);
        eventStore.assignEvent(this);
    }

    public void addEventDesign(EventDesign eventDesign) {
        eventDesignList.add(eventDesign);
        eventDesign.assignEvent(this);
    }

    public void removeEventDesign(EventDesign design) {
        eventDesignList.remove(design);
    }

    public void removeEventStore(EventStore store) {
        eventStoreList.remove(store);
    }

    public void update(String title, String address, LocalDate eventDate) {
        this.title = title;
        this.address = address;
        this.eventDate = eventDate;
    }
}