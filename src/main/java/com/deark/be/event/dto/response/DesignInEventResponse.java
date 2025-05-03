package com.deark.be.event.dto.response;

import com.deark.be.design.domain.Design;
import com.deark.be.event.domain.EventDesign;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record DesignInEventResponse(
        @Schema(description = "디자인 ID", example = "1")
        Long designId,
        @Schema(description = "디자인을 업로드한 스토어 이름", example = "케이크하우스")
        String storeName,
        @Schema(description = "디자인 이름", example = "생일 케이크 3단")
        String designName,
        @Schema(description = "디자인 이미지 URL", example = "https://cdn.deark.com/designs/3tier.png")
        String designImageUrl,
        @Schema(description = "해당 디자인에 대한 메모", example = "예쁘게 장식해 주세요")
        String memo
) {
    public static DesignInEventResponse from(EventDesign eventDesign) {
        Design design = eventDesign.getDesign();
    return DesignInEventResponse.builder()
            .designId(design.getId())
            .storeName(design.getStore().getName())
            .designName(design.getName())
            .designImageUrl(design.getImageUrl())
            .memo(eventDesign.getMemo())
            .build();
    }
}
