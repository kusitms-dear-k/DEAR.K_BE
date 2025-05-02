package com.deark.be.design.service;

import com.deark.be.design.dto.response.SearchDesignResponse;
import com.deark.be.design.dto.response.SearchDesignResponseList;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.store.domain.type.SortType;
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

     public SearchDesignResponseList getDesignList(Long page, Long count, SortType sortType,
                                                   String keyword, Boolean isSameDayOrder, List<String> locationList,
                                                   LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice, Boolean isLunchBoxCake) {

            List<SearchDesignResponse> allSearchResult = designRepository.findAllDesignByCriteria(page, count, sortType,
                    keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isLunchBoxCake);

            boolean hasNext = allSearchResult.size() == count + 1;

            if (hasNext) {
                allSearchResult.remove(allSearchResult.size() - 1);
            }

            return SearchDesignResponseList.of(page, hasNext, allSearchResult);
     }
}
