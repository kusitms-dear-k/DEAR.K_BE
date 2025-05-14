package com.deark.be.order.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.order.dto.response.OrderQuestionResponseList;
import com.deark.be.order.service.OrderQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "주문 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderQuestionService orderService;

    @Operation(summary = "주문서 질문 양식 조회", description = "특정 가게의 주문서 질문 양식을 조회합니다.")
    @GetMapping("/form/{storeId}")
    public ResponseEntity<ResponseTemplate<OrderQuestionResponseList>> getOrderForm(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long storeId) {
        OrderQuestionResponseList responseList = orderService.getOrderFormQuestions(storeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

}
