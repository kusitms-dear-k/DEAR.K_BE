package com.deark.be.order.repository;

import com.deark.be.order.domain.QA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QARepository extends JpaRepository<QA, Long> {
}
