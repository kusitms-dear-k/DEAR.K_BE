package com.deark.be.order.dto.response;

import com.deark.be.order.domain.OrderRequestForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record MyOrderAcceptedResponse(
        @Schema(description = "픽업 시간", example = "13시 00분")
        String pickUpTime,
        @Schema(description = "최종 금액", example = "24500")
        String price,
        @Schema(description = "은행 로고 URL", example = "https://deark.com/logo.png")
        String bankImageUrl,
        @Schema(description = "은행 이름", example = "신한은행")
        String bankName,
        @Schema(description = "계좌 번호", example = "123-456-789012")
        String account
) {
    public static MyOrderAcceptedResponse of(OrderRequestForm orderRequestForm, String pickUpTime) {
        return MyOrderAcceptedResponse.builder()
                .pickUpTime(pickUpTime)
                .price(orderRequestForm.getMakerResponse())
                .bankImageUrl(orderRequestForm.getStore().getUser().getBank().getImageUrl())
                .bankName(orderRequestForm.getStore().getUser().getBank().getName())
                .account(orderRequestForm.getStore().getUser().getAccount())
                .build();
    }
}