package com.jean.blue.web.admin;

import com.jean.blue.domain.enumeration.Role;
import com.jean.blue.domain.enumeration.mapper.EnumMapperValue;
import com.jean.blue.service.MeetingService;
import com.jean.blue.service.MemberService;
import com.jean.blue.web.dto.meeting.MeetingResponseDto;
import com.jean.blue.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final MemberService memberService;
    private final MeetingService meetingService;

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("users", memberService.findAllDesc());
        return "admin/user/user";
    }

    @GetMapping("/user/userCreate")
    public String userCreate() {
        return "admin/user/user-create";
    }

    @GetMapping("/user/userUpdate/{id}")
    public String userUpdate(@PathVariable Long id, Model model) {
        MemberResponseDto dto = memberService.findById(id);
        model.addAttribute("user", dto);
        model.addAttribute("roles", Arrays.stream(Role.values())
                                                        .map(EnumMapperValue::new)
                                                        .collect(Collectors.toList()));
        return "admin/user/user-update";
    }


    @GetMapping("/meeting/meetingCreate")
    public String meetingCreate() {
        return "admin/meeting/meeting-create";
    }

    @GetMapping("/meeting/{id}")
    public String meetingDetail(@PathVariable Long id, Model model) {
        memberService.findAllDesc();
        return "admin/user/user";
    }

    @GetMapping("/meeting/meetingUpdate/{id}")
    public String meetingUpdate(@PathVariable Long id, Model model) {
        MeetingResponseDto dto = meetingService.findById(id);
        model.addAttribute("meeting", dto);
        return "admin/meeting/meeting-update";
    }


}
