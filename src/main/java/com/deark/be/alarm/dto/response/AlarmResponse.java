package com.deark.be.alarm.dto.response;

import com.deark.be.order.domain.type.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AlarmResponse(
        Long alarmId,
        String designImageUrl,
        String storeName,
        OrderStatus orderStatus,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime alarmDateTime,
        Long messageId,
        Boolean isRead
) {
}