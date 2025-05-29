package com.deark.be.order.repository;

import com.deark.be.order.domain.OrderQuestion;
import com.deark.be.store.domain.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderQuestionRepository extends JpaRepository<OrderQuestion, Long> {
    List<OrderQuestion> findAllByStore(Store store);
}
