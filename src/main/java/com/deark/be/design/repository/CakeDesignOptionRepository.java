package com.deark.be.design.repository;

import com.deark.be.design.domain.CakeDesignOption;
import com.deark.be.design.domain.type.OptionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeDesignOptionRepository extends JpaRepository<CakeDesignOption, Long> {
    boolean existsByCakeDesignStoreIdAndValueIsContainingAndOptionCategory(Long storeId, String keyword, OptionCategory optionCategory);
}
