package com.deark.be.store.repository;

import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    void deleteByStore(Store store);
}
