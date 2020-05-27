package com.jean.blue.web.dto.member;

import com.jean.blue.domain.enumeration.Role;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {
    private Long memberId;
    private String userId;
    private String password;
    private String username;
    private String mobileNumber;
    private String nickname;
    private Role role;
    private LocalDateTime createdDate;

    @Builder
    public MemberResponseDto(Member entity){
        this.memberId = entity.getMemberId();
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.username = entity.getUsername();
        this.mobileNumber = entity.getMobileNumber();
        this.nickname = entity.getNickname();
        this.role = entity.getRole();
        this.createdDate = entity.getCreatedDate();
    }
}
