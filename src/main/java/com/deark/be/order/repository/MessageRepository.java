package com.deark.be.order.repository;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.Status;
import com.deark.be.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Long countByUserAndStatus(User user, Status status);
}
