package com.deark.be.store.dto.response;

import com.deark.be.store.domain.BusinessHours;
import java.time.format.DateTimeFormatter;

public record PickUpHourResponse(
        String dayName,
        String startTime,
        String endTime
) {
    public static PickUpHourResponse from(BusinessHours businessHours) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return new PickUpHourResponse(
                businessHours.getBusinessDay().getDayName(),
                businessHours.getOpenTime().format(formatter),
                businessHours.getCloseTime().format(formatter)
        );
    }
}
