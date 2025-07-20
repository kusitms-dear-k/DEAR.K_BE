package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.QA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QARepository extends JpaRepository<QA, Long> {

    List<QA> findAllByOrderRequestForm(OrderRequestForm orderRequestForm);
}
