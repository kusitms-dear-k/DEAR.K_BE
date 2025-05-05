package com.deark.be.design.repository;

import com.deark.be.design.domain.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignRepository extends JpaRepository<Design, Long>, DesignRepositoryCustom {

    @Query(value = "SELECT * FROM design ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<Design> findRandomDesigns(@Param("count") long count);
}
