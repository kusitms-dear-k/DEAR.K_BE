package com.deark.be.design.service;

import com.deark.be.design.dto.response.SearchDesignResponse;
import com.deark.be.design.repository.DesignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DesignService {

     private final DesignRepository designRepository;

     public List<SearchDesignResponse> getDesignList(String keyword, Boolean isSameDayOrder, List<String> locationList,
                                                     LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice,
                                                     Boolean isUnmanned, String isLunchBoxCake) {

            return designRepository.findAllDesignByCriteria(keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isUnmanned, isLunchBoxCake);
     }
}
