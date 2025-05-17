package com.deark.be.order.repository;

import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QARepository extends JpaRepository<QA, Long> {

    List<QA> findAllByMessage(Message message);
}
