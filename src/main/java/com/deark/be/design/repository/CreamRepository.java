package com.deark.be.design.repository;

import com.deark.be.design.domain.Cream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreamRepository extends JpaRepository<Cream, Long> {
}
