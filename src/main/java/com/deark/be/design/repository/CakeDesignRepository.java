package com.deark.be.design.repository;

import com.deark.be.design.domain.CakeDesign;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeDesignRepository extends JpaRepository<CakeDesign, Long>, CakeDesignRepositoryCustom {

    @Query("SELECT d.id FROM CakeDesign d LEFT JOIN d.eventDesignList ed GROUP BY d.id ORDER BY COUNT(ed) DESC")
    List<Long> findTopDesignIds(Pageable pageable);

    List<CakeDesign> findByStoreId(Long storeId);
}
