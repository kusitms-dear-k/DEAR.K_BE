package com.deark.be.store.repository;

import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {

    List<BusinessHours> findAllByStore(Store store);
}
