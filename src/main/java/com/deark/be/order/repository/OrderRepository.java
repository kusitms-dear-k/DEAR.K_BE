package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderForm, Long> {
}
