package com.deark.be.store.repository;

import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.domain.type.BusinessDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {

    List<BusinessHours> findAllByStore(Store store);

    Optional<BusinessHours> findByStoreAndBusinessDay(Store store, BusinessDay businessDay);
}
