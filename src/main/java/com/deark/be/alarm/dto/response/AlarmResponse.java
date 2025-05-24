package com.deark.be.alarm.dto.response;

import com.deark.be.order.domain.type.OrderStatus;
import com.deark.be.order.domain.type.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AlarmResponse(
        @Schema(description = "알림 ID", example = "1")
        Long alarmId,
        @Schema(description = "디자인 이미지 URL", example = "https://cdn.deark.com/designs/3tier.png")
        String designImageUrl,
        @Schema(description = "가게 이름", example = "디어케이 케이크")
        String storeName,
        @Schema(description = "주문 상태", example = "PENDING")
        OrderStatus orderStatus,
        @Schema(description = "알림 생성 날짜", example = "2023-10-01 12:00:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime alarmDateTime,
        @Schema(description = "알림 메시지 ID", example = "1")
        Long messageId,
        @Schema(description = "알림 읽음 여부", example = "true")
        Boolean isRead,
        @Schema(description = "알림 응답 상태", example = "CANCELED")
        ResponseStatus responseStatus
) {
}