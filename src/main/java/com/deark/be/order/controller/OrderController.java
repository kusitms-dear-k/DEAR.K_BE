package com.deark.be.order.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.order.dto.response.MyOrderCountResponseList;
import com.deark.be.order.dto.response.MyOrderPendingResponse;
import com.deark.be.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Order", description = "주문 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "내 요청서 상태 별 개수 조회", description = "내 요청서의 응답 대기 / 수락 / 반려 각각의 개수를 조회합니다.")
    @GetMapping("/count")
    public ResponseEntity<ResponseTemplate<MyOrderCountResponseList>> getStoreDetail(
            @AuthenticationPrincipal Long userId) {

        MyOrderCountResponseList responseList = orderService.getAllCountByStatus(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "응답 대기 중인 모든 내 요청서 조회", description = "응답 대기 중인 내 요청서를 조회합니다.")
    @GetMapping("/pending")
    public ResponseEntity<ResponseTemplate<List<MyOrderPendingResponse>>> getWaitingOrders(
            @AuthenticationPrincipal Long userId) {

        List<MyOrderPendingResponse> responseList = orderService.getAllPendingOrders(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }
}
