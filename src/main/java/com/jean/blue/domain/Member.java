package com.jean.blue.domain;

import com.jean.blue.domain.BaseTimeEntity;
import com.jean.blue.domain.enumeration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"userId"})})
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private int isDeleted;

    @Builder
    public Member(Long memberId, String userId, String password, String username, String mobileNumber, String nickname, Role role) {
        this.memberId = memberId;
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.nickname = nickname;
        this.role = role;
    }

    public void update(String username, String mobileNumber, String nickname, Role role){
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.nickname = nickname;
        this.role = role;
    }


    public void profileUpdate(String username, String mobileNumber, String nickname){
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.nickname = nickname;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void changeRole(Role role){
        this.role = role;
    }

    public void delete(int isDeleted){
        this.isDeleted = isDeleted;
    }



}
