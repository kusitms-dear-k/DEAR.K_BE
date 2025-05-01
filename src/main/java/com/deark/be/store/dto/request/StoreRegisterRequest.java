package com.deark.be.store.dto.request;

import com.deark.be.store.domain.Store;
import com.deark.be.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "가게 입점 등록 요청 DTO")
public record StoreRegisterRequest(

        @Schema(description = "가게 개업일자 (yyyy-MM-dd)", example = "2024-05-01")
        @NotNull LocalDate establishDate,

        @Schema(description = "사업자 등록번호", example = "1234567890")
        @NotBlank String businessNumber,

        @Schema(description = "정산용 계좌번호", example = "110-123-456789")
        @NotBlank String settlementAccount,

        @Schema(description = "사업자등록증 파일", type = "string", format = "binary")
        @NotNull MultipartFile businessLicenseFile,

        @Schema(description = "영업신고증 파일", type = "string", format = "binary")
        @NotNull MultipartFile businessPermitFile,

        @Schema(description = "가게 이름", example = "이쁜케이크")
        @NotBlank String ownerName

) {
        public Store toEntity(User user, String businessLicenseUrl, String businessPermitUrl) {
                return Store.builder()
                        .user(user)
                        .establishDate(establishDate)
                        .businessNumber(businessNumber)
                        .settlementAccount(settlementAccount)
                        .ownerName(ownerName)
                        .businessLicenseUrl(businessLicenseUrl)
                        .businessPermitUrl(businessPermitUrl)
                        .build();
        }
}
