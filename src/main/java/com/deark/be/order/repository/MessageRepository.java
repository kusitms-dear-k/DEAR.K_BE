package com.deark.be.order.repository;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.OrderStatus;
import com.deark.be.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCustom {
    Long countByUserAndOrderStatus(User user, OrderStatus orderStatus);

    List<Message> findAllByUserAndOrderStatus(User user, OrderStatus orderStatus);
}
