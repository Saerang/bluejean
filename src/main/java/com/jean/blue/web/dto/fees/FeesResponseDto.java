package com.jean.blue.web.dto.fees;

import com.jean.blue.domain.Fees;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeesResponseDto {
    private Long feesId;
    private Member member;
    private int fee;
    private String feeYear;
    private String feeMonth;

    @Builder
    public FeesResponseDto(Fees entity){
        this.feesId = entity.getFeesId();
        this.member = entity.getMember();
        this.fee = entity.getFee();
        this.feeYear = entity.getFeeMonth();
        this.feeMonth = entity.getFeeMonth();
    }
}
