package com.deark.be.order.dto.response;

import com.deark.be.design.domain.Design;
import com.deark.be.order.domain.Message;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record MyOrderPendingResponse(
        Long messageId,
        LocalDate createdAt,
        String storeName,
        String designName,
        String designImageUrl,
        Map<String, String> qaDetails
) {
    public static MyOrderPendingResponse of(Message message, Map<String, String> qaMap) {
        Design design = message.getDesign();

        return MyOrderPendingResponse.builder()
                .messageId(message.getId())
                .createdAt(message.getCreatedAt().toLocalDate())
                .storeName(message.getStore().getName())
                .designName(design != null ? design.getName() : "")
                .designImageUrl(design != null ? design.getImageUrl() : "")
                .qaDetails(qaMap)
                .build();
    }
}