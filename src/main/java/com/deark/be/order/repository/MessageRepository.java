package com.deark.be.order.repository;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.Status;
import com.deark.be.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCustom {
    Long countByUserAndStatus(User user, Status status);

    List<Message> findAllByUserAndStatus(User user, Status status);
}
