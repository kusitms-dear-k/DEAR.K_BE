package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderQuestion, Long> {
}
