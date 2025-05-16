package com.deark.be.order.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.order.dto.request.SubmitOrderRequest;
import com.deark.be.order.dto.response.BusinessHoursResponse;
import com.deark.be.order.dto.response.OrderQuestionResponseList;
import com.deark.be.order.dto.response.PickUpDateResponseList;
import com.deark.be.order.service.OrderQuestionService;
import com.deark.be.order.service.OrderService;
import com.deark.be.store.dto.response.DesignCreamResponseList;
import com.deark.be.store.dto.response.DesignSheetResponseList;
import com.deark.be.store.dto.response.DesignSizeResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @Operation(summary = "가게 운영 요일 조회", description = "가게 운영 요일을 조회합니다.")
    @GetMapping("/store/{storeId}/business-day")
    public ResponseEntity<ResponseTemplate<PickUpDateResponseList>> getBusinessHours(
            @PathVariable Long storeId) {

        PickUpDateResponseList businessHours = orderService.getPickUpDate(storeId);

        return ResponseEntity.ok(ResponseTemplate.from(businessHours));
    }

    @Operation(summary = "픽업 날짜의 가게 운영시간 조회", description = "픽업 날짜의 가게 운영시간을 조회합니다. <br>" +
            "픽업날짜에 2025-01-01 형식으로 넣어주세요.")
    @GetMapping("/store/{storeId}/business-hours")
    public ResponseEntity<ResponseTemplate<BusinessHoursResponse>> getBusinessHours(
            @PathVariable Long storeId,
            @RequestParam LocalDate pickUpDate) {

        BusinessHoursResponse businessHours = orderService.getBusinessHours(storeId, pickUpDate);

        return ResponseEntity.ok(ResponseTemplate.from(businessHours));
    }

    @Operation(summary = "가게의 모든 디자인 사이즈 조회", description = "가게의 모든 디자인 사이즈를 조회합니다.")
    @GetMapping("/store/{storeId}/size")
    public ResponseEntity<ResponseTemplate<DesignSizeResponseList>> getDesignSize(
            @PathVariable Long storeId) {

        DesignSizeResponseList designSize = orderService.getDesignSize(storeId);

        return ResponseEntity.ok(ResponseTemplate.from(designSize));
    }

    @Operation(summary = "가게의 모든 디자인 크림 조회", description = "가게의 모든 디자인 크림을 조회합니다.")
    @GetMapping("/store/{storeId}/cream")
    public ResponseEntity<ResponseTemplate<DesignCreamResponseList>> getDesignCream(
            @PathVariable Long storeId) {

        DesignCreamResponseList designCream = orderService.getDesignCream(storeId);

        return ResponseEntity.ok(ResponseTemplate.from(designCream));
    }

    @Operation(summary = "가게의 모든 디자인 시트 조회", description = "가게의 모든 디자인 시트를 조회합니다.")
    @GetMapping("/store/{storeId}/sheet")
    public ResponseEntity<ResponseTemplate<DesignSheetResponseList>> getDesignSheet(
            @PathVariable Long storeId) {

        DesignSheetResponseList designSheet = orderService.getDesignSheet(storeId);

        return ResponseEntity.ok(ResponseTemplate.from(designSheet));
    }
}
