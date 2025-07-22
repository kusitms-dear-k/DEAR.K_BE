package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.OrderRequestFormQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QARepository extends JpaRepository<OrderRequestFormQa, Long> {

    List<OrderRequestFormQa> findAllByOrderRequestForm(OrderRequestForm orderRequestForm);
}
