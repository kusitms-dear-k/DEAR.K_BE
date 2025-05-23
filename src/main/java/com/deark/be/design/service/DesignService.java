package com.deark.be.design.service;

import static com.deark.be.design.exception.errorcode.DesignErrorCode.DESIGN_NOT_FOUND;

import com.deark.be.design.domain.Design;
import com.deark.be.design.dto.response.*;
import com.deark.be.design.exception.DesignException;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.event.repository.EventDesignRepository;
import com.deark.be.store.domain.type.SortType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DesignService {

     private final DesignRepository designRepository;
     private final EventDesignRepository eventDesignRepository;

     public SearchDesignResponseList getDesignList(Long userId, Long page, Long count, SortType sortType,
                                                   String keyword, Boolean isSameDayOrder, List<String> locationList,
                                                   LocalDate startDate, LocalDate endDate, Long minPrice, Long maxPrice,
                                                   Boolean isSelfService, Boolean isLunchBoxCake) {

        SearchDesignPagedResult allSearchResult = designRepository.findAllDesignByCriteria(userId, page, count, sortType,
                keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isSelfService, isLunchBoxCake);

        boolean hasNext = allSearchResult.designList().size() == count + 1;

        if (hasNext) {
            allSearchResult.designList().remove(allSearchResult.designList().size() - 1);
        }

        return SearchDesignResponseList.of(allSearchResult.totalCount(), page, hasNext, allSearchResult.designList());
     }

    public RecommendDesignResponseList getRecommendDesignList(Long userId, Long count) {
        Pageable topN = PageRequest.of(0, count.intValue());
        List<Long> popularDesignIds = designRepository.findTopDesignIds(topN);

        List<Design> designList = designRepository.findAllById(popularDesignIds);

        Map<Long, Design> designMap = designList.stream()
                .collect(Collectors.toUnmodifiableMap(Design::getId, Function.identity()));

        List<RecommendDesignResponse> recommendDesigns = popularDesignIds.stream()
                .map(designMap::get)
                .map(design -> {
                    boolean isLiked = eventDesignRepository.existsByEventUserIdAndDesignId(userId, design.getId());
                    return RecommendDesignResponse.of(design, isLiked);})
                .toList();

        return RecommendDesignResponseList.from(recommendDesigns);
    }

    public StoreDesignResponseList getStoreDesignList(Long userId, Long page, Long count, Long storeId, String sizeName) {
         List<StoreDesignResponse> responseList = designRepository.findAllDesignBySizeAndStoreId(userId, page, count, storeId, sizeName);

        boolean hasNext = responseList.size() == count + 1;

        if (hasNext) {
            responseList.remove(responseList.size() - 1);
        }

         return StoreDesignResponseList.of(page, hasNext, responseList);
    }

    public DesignDetailResponse getDesignDetail(Long userId, Long designId) {
        return designRepository.findDesignDetailById(userId, designId);
    }

    public Design getDesignByIdOrThrow(Long designId) {
        return designRepository.findById(designId)
                .orElseThrow(() -> new DesignException(DESIGN_NOT_FOUND));
    }

    public List<StoreDesignSimpleResponse> getSimpleDesignListByStoreId(Long storeId) {
        List<Design> designs = designRepository.findByStoreId(storeId);
        return designs.stream()
                .map(StoreDesignSimpleResponse::from)
                .toList();
    }
}
