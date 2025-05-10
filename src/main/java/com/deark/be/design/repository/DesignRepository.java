package com.deark.be.design.repository;

import com.deark.be.design.domain.Design;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignRepository extends JpaRepository<Design, Long>, DesignRepositoryCustom {

    @Query("SELECT d.id FROM Design d LEFT JOIN d.eventDesignList ed GROUP BY d.id ORDER BY COUNT(ed) DESC")
    List<Long> findTopDesignIds(Pageable pageable);
}
