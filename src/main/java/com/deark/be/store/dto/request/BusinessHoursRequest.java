package com.deark.be.store.dto.request;

import com.deark.be.store.domain.type.BusinessDay;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

@Schema(description = "영업시간 요청 DTO")
public record BusinessHoursRequest(

        @Schema(description = "요일", example = "MONDAY")
        @NotNull BusinessDay businessDay,

        @Schema(description = "영업 시작 시간", example = "09:00", pattern = "HH:mm")
        @NotNull LocalTime openTime,

        @Schema(description = "영업 종료 시간", example = "18:00", pattern = "HH:mm")
        @NotNull LocalTime closeTime,

        @Schema(description = "24시간 운영 여부", example = "false")
        @NotNull Boolean isOpen24Hours

) {}
