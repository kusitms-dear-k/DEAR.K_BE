package com.deark.be.store.dto.response;

import com.deark.be.store.domain.BusinessHours;
import java.time.format.DateTimeFormatter;
import lombok.Builder;

@Builder
public record PickUpHourResponse(
        String dayName,
        String startTime,
        String endTime
) {
    public static PickUpHourResponse from(BusinessHours businessHours) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return PickUpHourResponse.builder()
                .dayName(businessHours.getBusinessDay().getDayName())
                .startTime(businessHours.getOpenTime().format(formatter))
                .endTime(businessHours.getCloseTime().format(formatter))
                .build();
    }
}
