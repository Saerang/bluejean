package com.jean.blue.web.dto.fees;

import com.jean.blue.domain.Fees;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FeesListRequestDto {
    private Long feesId;
    private Member member;
    private int fee;
    private String feeYear;
    private String feeMonth;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public FeesListRequestDto(Fees entity){
        this.feesId = entity.getFeesId();
        this.member = entity.getMember();
        this.fee = entity.getFee();
        this.feeYear = entity.getFeeYear();
        this.feeMonth = entity.getFeeMonth();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
