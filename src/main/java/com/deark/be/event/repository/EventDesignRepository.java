package com.deark.be.event.repository;

import com.deark.be.design.domain.Design;
import com.deark.be.event.domain.EventDesign;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDesignRepository  extends JpaRepository<EventDesign, Long> {

    Optional<EventDesign> findTopByEventIdOrderByCreatedAtAsc(Long eventId);

    Optional<EventDesign> findByEventIdAndDesignId(Long eventId, Long designId);

    List<EventDesign> findAllByDesign(Design design);

    boolean existsByEventIdAndDesignId(Long eventId, Long designId);

    @Query("SELECT ed.design.id FROM EventDesign ed WHERE ed.event.user.id = :userId")
    List<Long> findDesignIdsByUserId(@Param("userId") Long userId);
}
