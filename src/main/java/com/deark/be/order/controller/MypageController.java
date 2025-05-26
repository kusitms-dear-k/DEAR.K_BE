package com.deark.be.order.controller;

import com.deark.be.global.dto.ResponseTemplate;
import com.deark.be.order.domain.type.OrderStatus;
import com.deark.be.order.domain.type.ResponseStatus;
import com.deark.be.order.dto.response.*;
import com.deark.be.order.service.MypageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.deark.be.global.dto.ResponseTemplate.EMPTY_RESPONSE;

@Tag(name = "Mypage", description = "마이페이지 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    @Operation(summary = "내 요청서 상태 별 개수 조회", description = "내 요청서의 응답 대기 / 수락 / 반려 각각의 개수를 조회합니다.")
    @GetMapping("/request/count")
    public ResponseEntity<ResponseTemplate<MyOrderCountResponseList>> getStoreDetail(
            @AuthenticationPrincipal Long userId) {

        MyOrderCountResponseList responseList = mypageService.getAllCountByStatus(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "내 요청서 상태 별 전체 조회", description = "내 요청서의 응답 대기 / 수락 / 반려 각각의 상태별 모든 내 요청서를 조회합니다.")
    @GetMapping("/request/status")
    public ResponseEntity<ResponseTemplate<MyOrderStatusResponseList>> getMyOrdersByStatus(
            @AuthenticationPrincipal Long userId,
            @RequestParam(defaultValue = "PENDING") OrderStatus orderStatus) {

        MyOrderStatusResponseList responseList = mypageService.getAllMyOrdersByStatus(userId, orderStatus);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "반려된 견적서 사유 조회", description = "반려된 견적서의 사유를 조회합니다. <br>" +
            "테스트 하려면 ID : 3 또는 6으로 조회하면 됩니다.")
    @GetMapping("/request/rejected/{messageId}")
    public ResponseEntity<ResponseTemplate<MyOrderRejectedResponse>> getRejectReason(
            @PathVariable Long messageId) {

        MyOrderRejectedResponse response = mypageService.getRejectedOrderReason(messageId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }

    @Operation(summary = "견적서 또는 주문서 상세 조회", description = "견적서 또는 주문서의 상세 정보를 조회합니다. <br>" +
            "designType : STORE 이면 가게의 디자인 선택 / CUSTOM 이면 갤러리에서 업로드 <br>" +
            "추가 요청사항에 사진이 있을 경우 requestDetailImageUrl 칼럼이 추가로 제공됩니다. (테스트 ID : 7) <br><br>" +
            "테스트 ID : 1 or 4 or 7 <br>")
    @GetMapping("/request/detail/{messageId}")
    public ResponseEntity<ResponseTemplate<MyOrderDetailResponse>> getOrderDetail(
            @PathVariable Long messageId) {

        MyOrderDetailResponse response = mypageService.getOrderDetail(messageId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }

    @Operation(summary = "수락된 주문서 상세 조회", description = "수락된 주문서의 메이커 답변, 견적서 상세 정보, 카카오톡 링크를 조회합니다. <br>" +
            "테스트 하려면 ID : 2로 조회하면 됩니다.")
    @GetMapping("/request/accepted/{messageId}")
    public ResponseEntity<ResponseTemplate<MyOrderAcceptedResponse>> getAcceptedOrderDetail(
            @PathVariable Long messageId) {

        MyOrderAcceptedResponse response = mypageService.getAcceptedOrderDetail(messageId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }

    @Operation(summary = "픽업 예정 주문서 조회", description = "로그인한 사용자의 픽업 예정(예약 완료, 베이킹 완료) 상태의 주문서 리스트를 조회합니다.")
    @GetMapping("/pickup/scheduled")
    public ResponseEntity<ResponseTemplate<OrderManagementResponseList>> getPickupScheduledMessages(
            @AuthenticationPrincipal Long userId){
        OrderManagementResponseList responseList= mypageService.getPickupScheduledOrders(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "픽업 완료 주문서 조회", description = "로그인한 사용자의 픽업 완료(PICKUP_DONE) 상태의 주문서를 조회합니다.")
    @GetMapping("/pickup/completed")
    public ResponseEntity<ResponseTemplate<OrderManagementResponseList>> getPickupCompletedMessages(
            @AuthenticationPrincipal Long userId) {
        OrderManagementResponseList responseList = mypageService.getPickupCompletedOrders(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responseList));
    }

    @Operation(summary = "주문서 관련 피커 응답 변경", description = "주문서 관련 피커 응답을 변경합니다. <br>" +
            "responseStatus : CANCELED(주문 취소), PAID(입금 완료) 중 하나로 입력해주세요.")
    @PutMapping("/request/{messageId}")
    public ResponseEntity<ResponseTemplate<Object>> updateResponseStatus(
            @PathVariable Long messageId,
            @RequestParam ResponseStatus status) {

        mypageService.updateResponseStatus(messageId, status);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EMPTY_RESPONSE);
    }

    @Operation(summary = "다가오는 가장 가까운 이벤트 조회", description = "오늘 날짜 기준으로 가장 가까운 미래 또는 오늘의 이벤트를  D-day와 함께 마이페이지에 표시합니다. <br>"
            + "다가오는 이벤트가 없으면 null을 반환합니다.")
    @GetMapping("/event/upcoming")
    public ResponseEntity<ResponseTemplate<UpcomingEventResponse>> getUpcomingEvent(
            @AuthenticationPrincipal Long userId) {
        UpcomingEventResponse response = mypageService.getUpcomingEvent(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }
}
