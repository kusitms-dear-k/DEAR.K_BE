package com.deark.be.order.dto.response;

import com.deark.be.order.domain.type.MakeStatus;
import lombok.Builder;

@Builder
public record OrderManagementResponse(
        Long messageId,
        String storeName,
        String designName,
        String designUrl,
        String size,
        String cream,
        String sheet,
        MakeStatus makeStatus,
        String pickupDate,
        String pickupTime
) {
}
