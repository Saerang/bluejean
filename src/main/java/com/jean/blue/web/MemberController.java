package com.jean.blue.web;

import com.jean.blue.service.MemberService;
import com.jean.blue.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String member(Model model){
        model.addAttribute("members", memberService.findAllDesc());
        return "member/member";
    }

    @GetMapping("/memberDetail/{id}")
    public String memberDetail(@PathVariable Long id, Model model){
        MemberResponseDto member = memberService.findById(id);
        model.addAttribute("member", member);
        return "member/member-detail";
    }



}
