package com.deark.be.event.repository;

import com.deark.be.design.domain.CakeDesign;
import com.deark.be.event.domain.EventDesign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventDesignRepository  extends JpaRepository<EventDesign, Long> {

    Optional<EventDesign> findTopByEventIdOrderByCreatedAtAsc(Long eventId);

    Optional<EventDesign> findByEventIdAndDesignId(Long eventId, Long designId);

    List<EventDesign> findAllByDesign(CakeDesign cakeDesign);

    boolean existsByEventIdAndDesignId(Long eventId, Long designId);

    Boolean existsByEventUserIdAndDesignId(Long userId, Long designId);
}
