package com.deark.be.store.dto.request;

import com.deark.be.store.domain.Store;
import com.deark.be.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "가게 기본 정보 수정 요청 DTO")
public record StoreBasicInfoRequest(

        @Schema(description = "가게 이름", example = "이쁜케이크")
        @NotBlank String name,

        @Schema(description = "가게 전화번호", example = "010-1234-5678")
        @NotBlank String phone,

        @Schema(description = "가게 소개", example = "감성 케이크 가게입니다.")
        @NotBlank String description,

        @Schema(description = "가게 주소", example = "경기도 수원시 영통구")
        @NotBlank String address,

        @Schema(description = "대표 이미지 URL")
        @NotBlank String imageUrl,

        @Schema(description = "카카오톡 채팅 링크", example = "https://pf.kakao.com/_abcXj")
        @NotBlank String chattingUrl,

        @Schema(description = "무인 운영 여부", example = "false")
        @NotNull Boolean isUnmanned,

        @Schema(description = "주문 링크", example = "https://order.example.com")
        @NotBlank String orderLink,

        @Schema(description = "하루 최대 주문 수", example = "30")
        @NotNull Integer maxDailyOrders,

        @Schema(description = "영업시간 리스트")
        @NotNull List<BusinessHoursRequest> businessHours

) {
        public void updateBasicInfo(Store store) {
                store.updateBasicInfo(name,phone,description,address,imageUrl,chattingUrl,isUnmanned,orderLink,maxDailyOrders);
        }
}