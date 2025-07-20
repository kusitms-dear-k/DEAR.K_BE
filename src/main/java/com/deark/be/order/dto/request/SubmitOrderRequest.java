package com.deark.be.order.dto.request;

import com.deark.be.design.domain.CakeDesign;
import com.deark.be.order.domain.OrderRequestForm;
import com.deark.be.order.domain.type.DesignType;
import com.deark.be.order.domain.type.OrderStatus;
import com.deark.be.order.domain.type.RequestDetailType;
import com.deark.be.store.domain.Store;
import com.deark.be.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


public record SubmitOrderRequest(
        @Schema(description = "주문 대상 가게 ID", example = "1")
        Long storeId,

        @Schema(description = "디자인 선택 유형 (STORE: 가게 디자인, CUSTOM: 사용자 업로드한 디자인)", example = "STORE")
        DesignType designType,

        @Schema(description = "디자인 ID (designType이 STORE일 경우 사용)", example = "1")
        Long designId,

        @Schema(description = "추가 요청사항 이미지 선택 유형 (EVENT: 사용자가 찜한 디자인, CUSTOM: 사용자 업로드)", example = "CUSTOM")
        RequestDetailType requestDetailType,

        @Schema(description = "추가 요청사항 이미지의 디자인 ID (requestDetailType이 EVENT일 경우 사용)")
        Long requestDetailDesignId,

        @Schema(description = "질문 및 답변 리스트")
        List<QARequest> answers
) {
    public OrderRequestForm toEntity(User user, Store store, CakeDesign cakeDesign, CakeDesign requestDetailCakeDesign,
                                     String requestDetailImageUrl, String designUrl) {
        return OrderRequestForm.builder()
                .user(user)
                .store(store)
                .designType(designType)
                .requestDetailType(requestDetailType)
                .cakeDesign(cakeDesign)
                .requestDetailCakeDesign(requestDetailCakeDesign)
                .designUrl(designUrl)
                .requestDetailImageUrl(requestDetailImageUrl)
                .orderStatus(OrderStatus.PENDING)
                .build();
    }
}
