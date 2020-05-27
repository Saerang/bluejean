package com.jean.blue.web.dto.member;

import com.jean.blue.domain.enumeration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String username;
    private String password;
    private String mobileNumber;
    private String nickname;
    private Role role;
    private int isDeleted;

    @Builder
    public MemberUpdateRequestDto(String username, String password, String mobileNumber, String nickname, Role role, int isDeleted) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.nickname = nickname;
        this.role = role;
        this.isDeleted = isDeleted;
    }

    public void passwordEncoder(String password){
        this.password = password;
    }
}
