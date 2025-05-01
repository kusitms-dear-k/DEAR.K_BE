package com.deark.be.design.repository;

import com.deark.be.design.domain.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository extends JpaRepository<Design, Long>, DesignRepositoryCustom {
}
