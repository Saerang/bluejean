package com.jean.blue.web.dto.fees;

import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeesUpdateRequestDto {
    private Member member;
    private Long memberId;
    private int fee;
    private String feeYear;
    private String feeMonth;
    private int isDeleted;

    @Builder
    public FeesUpdateRequestDto(Member member, Long memberId, int fee, String feeYear, String feeMonth, int isDeleted){
        this.member = member;
        this.memberId = memberId;
        this.fee = fee;
        this.feeYear = feeYear;
        this.feeMonth = feeMonth;
        this.isDeleted = isDeleted;
    }

    public void setMember(Long memberId){
        this.member = Member.builder().memberId(memberId).build();
    }

}
