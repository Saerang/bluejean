package com.jean.blue.domain;

import com.jean.blue.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Fees extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feesId;

    @Column(nullable = false)
    private int fee;

    @Column(length = 4, nullable = false)
    private String feeYear;

    @Column(length = 2, nullable = false)
    private String feeMonth;

    private int isDeleted;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;


    @Builder
    public Fees(Member member, int fee, String feeYear, String feeMonth) {
        this.member = member;
        this.fee = fee;
        this.feeYear = feeYear;
        this.feeMonth = feeMonth;
    }

    public void update(Member member, int fee, String feeYear, String feeMonth){
        this.member = member;
        this.fee = fee;
        this.feeYear = feeYear;
        this.feeMonth = feeMonth;
    }

    public void delete(int isDeleted){
        this.isDeleted = isDeleted;
    }



}
