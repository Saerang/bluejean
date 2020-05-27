package com.jean.blue.web.dto.fees;

import com.jean.blue.domain.Fees;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeesSaveRequestDto {
    private Member member;
    private Long memberId;
    private int fee;
    private String feeYear;
    private String feeMonth;

    @Builder
    public FeesSaveRequestDto(Long memberId, int fee, String feeYear, String feeMonth){
        this.memberId = memberId;
        this.fee = fee;
        this.feeYear = feeYear;
        this.feeMonth = feeMonth;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public Fees toEntity() {
        return Fees.builder()
                .member(Member.builder().memberId(memberId).build())
                .fee(fee)
                .feeYear(feeYear)
                .feeMonth(feeMonth)
                .build();
    }

}
