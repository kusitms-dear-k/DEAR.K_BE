package com.deark.be.order.repository;

import com.deark.be.order.domain.CommonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, Long> {
}
