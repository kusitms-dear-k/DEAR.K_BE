package com.deark.be.order.dto.response;

import com.deark.be.order.domain.type.ProgressStatus;
import lombok.Builder;

@Builder
public record OrderManagementResponse(
        Long messageId,
        String storeName,
        String designName,
        String size,
        String cream,
        String sheet,
        ProgressStatus progressStatus,
        String pickupDate,
        String pickupTime
) {
}
