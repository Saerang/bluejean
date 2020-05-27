package com.jean.blue.service;

import com.jean.blue.domain.Member;
import com.jean.blue.repository.MemberRepository;
import com.jean.blue.web.dto.member.MemberListRequestDto;
import com.jean.blue.web.dto.member.MemberResponseDto;
import com.jean.blue.web.dto.member.MemberSaveRequestDto;
import com.jean.blue.web.dto.member.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(MemberSaveRequestDto requestDto) {
        requestDto.passwordEncoder(passwordEncoder.encode(requestDto.getPassword()));
        requestDto.initRole();
        return memberRepository.save(requestDto.toEntity()).getMemberId();
    }

    @Transactional
    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        Member User = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당유저가 없습니다. id = " + id));
        User.update(requestDto.getUsername(), requestDto.getMobileNumber(), requestDto.getNickname(), requestDto.getRole());
        return id;
    }

    @Transactional
    public Long profileUpdate(Long id, MemberUpdateRequestDto requestDto) {
        Member User = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당유저가 없습니다. id = " + id));
        User.profileUpdate(requestDto.getUsername(), requestDto.getMobileNumber(), requestDto.getNickname());
        return id;
    }

    @Transactional
    public Long changePassword(Long id, MemberUpdateRequestDto requestDto) {
        Member User = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당유저가 없습니다. id = " + id));
        requestDto.passwordEncoder(passwordEncoder.encode(requestDto.getPassword()));
        User.changePassword(requestDto.getPassword());
        return id;
    }

    @Transactional
    public Long delete(Long id, MemberUpdateRequestDto requestDto) {
        Member User = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당유저가 없습니다. id = " + id));
        User.delete(requestDto.getIsDeleted());
        return id;
    }

    public MemberResponseDto findById(Long id) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new MemberResponseDto(entity);
    }

    public MemberResponseDto findByUserId(String user_id) {
        Member entity = memberRepository.findByUserId(user_id);
        if (entity == null){
            throw new UsernameNotFoundException(user_id);
        }
        return new MemberResponseDto(entity);
    }

    public Member findMemberByUserId(String user_id) {
        Member entity = memberRepository.findByUserId(user_id);
        if (entity == null){
            throw new UsernameNotFoundException(user_id);
        }
        return entity;
    }

    public Member findMemberByMemberId(Long member_id) {
        Member entity = memberRepository.findById(member_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + member_id));
        return entity;
    }

    @Transactional(readOnly = true)
    public List<MemberListRequestDto> findAllDesc() {
        return memberRepository.findAllDesc().stream()
                .map(MemberListRequestDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId);

        if(member == null){
            throw new UsernameNotFoundException(userId);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().getRoleName()));

        return new User(member.getUserId(), member.getPassword(), authorities);
    }
}
