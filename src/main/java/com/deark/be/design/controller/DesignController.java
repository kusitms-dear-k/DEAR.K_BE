package com.deark.be.design.controller;

import com.deark.be.design.dto.response.SearchDesignResponseList;
import com.deark.be.design.service.DesignService;
import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.store.domain.type.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Design", description = "케이크 디자인 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/design")
public class DesignController {

    private final DesignService designService;

    @Operation(summary = "디자인 통합 검색", description = "page는 0부터 시작합니다. hasNext가 false이면 마지막 페이지입니다.<br><br>" +
            "입력받은 값 : keyword / 당일 주문 여부 : isSameDayOrder / 지역 리스트 : locationList (ex. 강남구, 중랑구) <br>" +
            "시작일 : '2025-01-01' 형식으로 startDate / 종료일 : '2025-01-01' 형식으로 endDate (하루만 선택할 경우 시작일과 종료일을 같게 입력해주세요.) <br>" +
            "최소 금액 : minPrice / 최대 금액 : maxPrice / 도시락 케이크 여부는 isLunchBoxCake 에 입력해주세요.")
    @GetMapping("/search")
    public ResponseEntity<ResponseTemplate<Object>> searchDesign(
            @AuthenticationPrincipal Long userId,
            @RequestParam(defaultValue = "0") Long page,
            @RequestParam(defaultValue = "6") Long count,
            @RequestParam(defaultValue = "ACCURACY") SortType sortType,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isSameDayOrder,
            @RequestParam(required = false) List<String> locationList,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Boolean isLunchBoxCake) {

        SearchDesignResponseList designList = designService.getDesignList(
                userId, page, count, sortType,
                keyword, isSameDayOrder, locationList, startDate, endDate, minPrice, maxPrice, isLunchBoxCake);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(designList));
    }
}
