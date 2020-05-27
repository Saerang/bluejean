package com.jean.blue.web;

import com.jean.blue.service.MeetingService;
import com.jean.blue.web.dto.meeting.MeetingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/meeting")
@Controller
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping("/meeting")
    public String meeting(Model model) {
        System.out.printf("/meeting/meeting");
        model.addAttribute("meetings", meetingService.findAllDesc());
        return "meeting/meeting";
    }

    @GetMapping("/meetingDetail/{id}")
    public String meetingDetail(@PathVariable Long id, Model model) {
        MeetingResponseDto meeting = meetingService.findById(id);
        model.addAttribute("meeting", meeting);
        model.addAttribute("meetingType", meeting.getType().getMeetingType());
        return "meeting/meeting-detail";
    }

    @GetMapping("/meetingCreate")
    public String meetingCreate() {
        return "admin/meeting/meeting-create";
    }

    @GetMapping("/meetingUpdate/{id}")
    public String meetingUpdate(@PathVariable Long id, Model model) {
        // MemberResponseDto dto = meetingService.findById(id);
        // model.addAttribute("meeting", dto);
        return "admin/meeting/meeting-update";
    }


}
