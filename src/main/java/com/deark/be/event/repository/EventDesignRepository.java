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

    Optional<EventDesign> findByEventIdAndCakeDesignId(Long eventId, Long designId);

    List<EventDesign> findAllByCakeDesign(CakeDesign cakeDesign);

    boolean existsByEventIdAndCakeDesignId(Long eventId, Long designId);

    Boolean existsByEventUserIdAndCakeDesignId(Long userId, Long designId);
}
