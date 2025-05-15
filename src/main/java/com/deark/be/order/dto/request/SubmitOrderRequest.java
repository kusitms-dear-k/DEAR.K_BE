package com.deark.be.order.dto.request;

import com.deark.be.design.domain.Design;
import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.type.DesignType;
import com.deark.be.order.domain.type.RequestDetailType;
import com.deark.be.order.domain.type.Status;
import com.deark.be.order.exception.OrderException;
import com.deark.be.order.exception.errorcode.OrderErrorCode;
import com.deark.be.store.domain.Store;
import com.deark.be.user.domain.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SubmitOrderRequest(
        @Schema(description = "주문 대상 가게 ID", example = "1")
        Long storeId,

        @Schema(description = "디자인 선택 유형 (STORE: 가게 디자인, CUSTOM: 사용자 업로드한 디자인)", example = "STORE")
        DesignType designType,

        @Schema(description = "디자인 ID (designType이 STORE일 경우 사용)", example = "1")
        Long designId,

        @Schema(description = "사용자가 직접 업로드한 디자인 이미지 URL (designType이 CUSTOM일 경우 사용)", example = "")
        String designUrl,

        @Schema(description = "추가 요청사항 이미지 선택 유형 (EVENT: 사용자가 찜한 디자인, CUSTOM: 사용자 업로드)", example = "CUSTOM")
        RequestDetailType requestDetailType,

        @Schema(description = "추가 요청사항 이미지의 디자인 ID (requestDetailType이 EVENT일 경우 사용)")
        Long requestDetailDesignId,

        @Schema(description = "추가 요청사항 직접 업로드 이미지 URL (requestDetailType이 CUSTOM일 경우 사용)", example = "https://image.bucket.com/request.jpg")
        String requestDetailImageUrl,

        @Schema(description = "질문 및 답변 리스트")
        List<QARequest> answers
) {
    public Message toEntity(User user, Store store, Design design, Design requestDetailDesign) {
        return Message.builder()
                .user(user)
                .store(store)
                .designType(designType)
                .requestDetailType(requestDetailType)
                .design(design)
                .requestDetailDesign(requestDetailDesign)
                .designUrl(designUrl)
                .requestDetailImageUrl(requestDetailImageUrl)
                .status(Status.PENDING)
                .build();
    }

    public void validateDesignParams() {
        if (designType == DesignType.STORE) {
            if (designId == null || designUrl != null) {
                throw new OrderException(OrderErrorCode.INVALID_STORE_DESIGN_CONFLICT);
            }
        } else if (designType == DesignType.CUSTOM) {
            if (designUrl == null || designUrl.isBlank() || designId != null) {
                throw new OrderException(OrderErrorCode.INVALID_CUSTOM_DESIGN_CONFLICT);
            }
        }
    }

    public void validateRequestDetailParams() {
        if (requestDetailType == RequestDetailType.EVENT) {
            if (requestDetailDesignId == null || requestDetailImageUrl != null) {
                throw new OrderException(OrderErrorCode.INVALID_EVENT_REQUEST_DETAIL_CONFLICT);
            }
        } else if (requestDetailType == RequestDetailType.CUSTOM) {
            if (requestDetailImageUrl == null || requestDetailImageUrl.isBlank() || requestDetailDesignId != null) {
                throw new OrderException(OrderErrorCode.INVALID_CUSTOM_REQUEST_DETAIL_CONFLICT);
            }
        }
    }
}
