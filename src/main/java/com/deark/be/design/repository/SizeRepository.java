package com.deark.be.design.repository;

import com.deark.be.design.domain.Size;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query("SELECT s.name FROM Size s WHERE s.design.store.id = :storeId")
    List<String> findSizeNamesByStoreId(@Param("storeId") Long storeId);
    boolean existsByStoreIdAndNameContaining(Long storeId, String keyword);
}
