package com.deark.be.event.repository;

import com.deark.be.event.domain.EventDesign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDesignRepository extends JpaRepository<EventDesign, Long> {
}
