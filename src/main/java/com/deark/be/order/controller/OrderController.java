package com.deark.be.order.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.order.dto.request.SubmitOrderRequest;
import com.deark.be.order.dto.response.OrderQuestionResponseList;
import com.deark.be.order.service.OrderQuestionService;
import com.deark.be.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "주문 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderQuestionService orderQuestionService;
    private final OrderService orderService;

    @Operation(summary = "주문서 질문 양식 조회", description = "특정 가게의 주문서 질문 양식을 조회합니다.")
    @GetMapping("/form/{storeId}")
    public ResponseEntity<ResponseTemplate<OrderQuestionResponseList>> getOrderForm(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long storeId) {
        OrderQuestionResponseList responseList = orderQuestionService.getOrderFormQuestions(storeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation( summary = "주문서 제출",
            description = """
        사용자가 작성한 주문서를 제출합니다.

        - designType이 `STORE`인 경우(= 가게 디자인 선택): designId 필수, designUrl은 null로 설정해주세요.
        - designType이 `CUSTOM`인 경우(= 사용자 갤러리에서 선택): designUrl 필수, designId은 null로 설정해주세요.

        - requestDetailType이 `EVENT`인 (= 찜한 디자인 선택): requestDetailDesignId 필수, requestDetailImageUrl은 null로 설정해주세요.
        - requestDetailType이 `CUSTOM`인 경우(= 사용자 갤러리에서 선택): requestDetailImageUrl 필수, requestDetailDesignId은 null로 설정해주세요.

        """)
    @PostMapping("/submit")
    public ResponseEntity<ResponseTemplate<Long>> submitOrder(
            @AuthenticationPrincipal Long userId,
            @RequestBody SubmitOrderRequest request) {

        Long messageId = orderService.submitOrder(request, userId);
        return ResponseEntity.ok(ResponseTemplate.from(messageId));
    }
}
