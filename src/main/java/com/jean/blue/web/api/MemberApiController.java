package com.jean.blue.web.api;

import com.jean.blue.domain.enumeration.Role;
import com.jean.blue.domain.enumeration.mapper.EnumMapperValue;
import com.jean.blue.service.MemberService;
import com.jean.blue.web.dto.member.MemberListRequestDto;
import com.jean.blue.web.dto.member.MemberResponseDto;
import com.jean.blue.web.dto.member.MemberSaveRequestDto;
import com.jean.blue.web.dto.member.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/member")
    public Long save(@RequestBody MemberSaveRequestDto requestDto){
        System.out.printf("member_save");
        Long returnId = memberService.save(requestDto);
        return returnId;
    }

    @PostMapping("/member/save")
    public Long memberSinUp(@RequestBody MemberSaveRequestDto requestDto){
        Long returnId = memberService.save(requestDto);
        return returnId;
    }

    @PutMapping("/member/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.update(id, requestDto);
    }

    @PutMapping("/profile/update/{id}")
    public Long profileUpdate(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.profileUpdate(id, requestDto);
    }

    @PutMapping("/password/update/{id}")
    public Long passwordUpdate(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.changePassword(id, requestDto);
    }


    @PutMapping("/member/delete/{id}")
    public Long delete(@PathVariable Long id,  @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.delete(id, requestDto);
    }

    @GetMapping("/member")
    public List<MemberListRequestDto> member() {
        List<MemberListRequestDto> memberListRequestDto = memberService.findAllDesc();
        return memberListRequestDto;
    }

    @GetMapping("/member/{id}")
    public MemberResponseDto findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @GetMapping("/member/role")
    public List<EnumMapperValue> memberRole(){
        return Arrays.stream(Role.values())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

}
