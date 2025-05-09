package com.deark.be.event.repository;

import com.deark.be.event.domain.EventStore;
import com.deark.be.store.domain.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStore,Long> {
    boolean existsByEventIdAndStoreId(Long eventId, Long storeId);
    Optional<EventStore> findTopByEventIdOrderByCreatedAtAsc(Long eventId);
    Optional<EventStore> findByEventIdAndStoreId(Long eventId, Long storeId);;
    List<EventStore> findAllByStore(Store store);
    Long countByStoreId(Long storeId);
}
