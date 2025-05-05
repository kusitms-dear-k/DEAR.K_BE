package com.deark.be.design.service;

import com.deark.be.design.domain.Design;
import com.deark.be.design.dto.response.RecommendDesignResponse;
import com.deark.be.design.dto.response.RecommendDesignResponseList;
import com.deark.be.design.dto.response.SearchDesignPagedResult;
import com.deark.be.design.dto.response.SearchDesignResponseList;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.event.repository.EventDesignRepository;
import com.deark.be.store.domain.type.SortType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DesignService {

     private final DesignRepository designRepository;
     private final EventDesignRepository eventDesignRepository;

     public SearchDesignResponseList getDesignList(Long userId, Long page, Long count, SortType sortType,
                                                   String keyword, Boolean isSameDayOrder, List<String> locationList,
                                                   LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice, Boolean isLunchBoxCake) {

            SearchDesignPagedResult allSearchResult = designRepository.findAllDesignByCriteria(userId, page, count, sortType,
                    keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isLunchBoxCake);

            boolean hasNext = allSearchResult.designList().size() == count + 1;

            if (hasNext) {
                allSearchResult.designList().remove(allSearchResult.designList().size() - 1);
            }

            return SearchDesignResponseList.of(allSearchResult.totalCount(), page, hasNext, allSearchResult.designList());
     }

    public RecommendDesignResponseList getRecommendDesignList(Long userId, Long count) {
        List<Design> randomDesigns = designRepository.findRandomDesigns(count);

        Set<Long> likedSet = (userId == 0L)
                ? Collections.emptySet()
                : new HashSet<>(eventDesignRepository.findDesignIdsByUserId(userId));

        List<RecommendDesignResponse> recommendDesigns = randomDesigns.stream()
                .map(design -> RecommendDesignResponse.of(design, likedSet.contains(design.getId())))
                .toList();

        return RecommendDesignResponseList.from(recommendDesigns);
    }
}
