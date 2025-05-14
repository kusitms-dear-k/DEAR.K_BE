package com.deark.be.order.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.order.domain.type.Status;
import com.deark.be.order.dto.response.MyOrderCountResponseList;
import com.deark.be.order.dto.response.MyOrderRejectedResponse;
import com.deark.be.order.dto.response.MyOrderStatusResponseList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mypage", description = "마이페이지 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final OrderService orderService;

    @Operation(summary = "내 요청서 상태 별 개수 조회", description = "내 요청서의 응답 대기 / 수락 / 반려 각각의 개수를 조회합니다.")
    @GetMapping("/request/count")
    public ResponseEntity<ResponseTemplate<MyOrderCountResponseList>> getStoreDetail(
            @AuthenticationPrincipal Long userId) {

        MyOrderCountResponseList responseList = orderService.getAllCountByStatus(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "내 요청서 상태 별 전체 조회", description = "내 요청서의 응답 대기 / 수락 / 반려 각각의 상태별 모든 내 요청서를 조회합니다.")
    @GetMapping("/request/status")
    public ResponseEntity<ResponseTemplate<MyOrderStatusResponseList>> getMyOrdersByStatus(
            @AuthenticationPrincipal Long userId,
            @RequestParam(defaultValue = "PENDING") Status status) {

        MyOrderStatusResponseList responseList = orderService.getAllMyOrdersByStatus(userId, status);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "반려된 견적서 사유 조회", description = "반려된 견적서의 사유를 조회합니다.")
    @GetMapping("/request/reject/{messageId}")
    public ResponseEntity<ResponseTemplate<MyOrderRejectedResponse>> getRejectReason(
            @RequestParam Long messageId) {

        MyOrderRejectedResponse response = orderService.getRejectedOrderReason(messageId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }
}