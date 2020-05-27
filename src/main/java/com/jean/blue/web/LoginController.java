package com.jean.blue.web;

import com.jean.blue.service.MemberService;
import com.jean.blue.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final MemberService memberService;


    @RequestMapping(value = {"/", "/login"})
    public String login(){
        return "login/login";
    }

    @RequestMapping(value = {"/login/error"})
    public String loginError(HttpServletRequest request, Model model){
        model.addAttribute("username", request.getAttribute("username"));
        model.addAttribute("errorMsg", request.getAttribute("errorMsg"));
        return "login/login";
    }

    // 회원가입 페이지
    @GetMapping("/signUp")
    public String signUp() {
        return "login/sign-up";
    }

    // 회원가입 페이지
    @GetMapping("/profile/update")
    public String profileUpdate(Principal principal, Model model){
        String user_id = principal.getName();
        MemberResponseDto member = memberService.findByUserId(user_id);
        model.addAttribute("member", member);
        return "login/profile-update";
    }

    // 회원가입 페이지
    @GetMapping("/profile/password/update")
    public String profilePasswordUpdate(Principal principal, Model model){
        String user_id = principal.getName();
        MemberResponseDto member = memberService.findByUserId(user_id);
        model.addAttribute("member", member);
        return "login/profile-password-update";
    }

}
