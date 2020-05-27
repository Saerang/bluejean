package com.jean.blue.web.dto.member;

import com.jean.blue.domain.enumeration.Role;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {

    private String userId;
    private String password;
    private String username;
    private String mobileNumber;
    private String nickname;
    private Role role;

    @Builder
    public MemberSaveRequestDto(String userId, String password, String username, String mobileNumber, String nickname) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.nickname = nickname;
        this.role = Role.NONE;
    }

    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .username(username)
                .mobileNumber(mobileNumber)
                .role(role)
                .nickname(nickname)
                .build();
    }

    public void passwordEncoder(String password){
        this.password = password;
    }

    public void initRole(){
        if(this.role == null)
            this.role = Role.NONE;
    }

}
