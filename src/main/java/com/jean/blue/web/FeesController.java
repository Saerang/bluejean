package com.jean.blue.web;

import com.jean.blue.service.FeesService;
import com.jean.blue.service.MemberService;
import com.jean.blue.web.dto.fees.FeesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/fees")
@Controller
public class FeesController {

    private final FeesService feesService;
    private final MemberService memberService;

    @GetMapping("/fees")
    public String fee(Model model) {
        model.addAttribute("fees", feesService.findAllDesc());
        return "fees/fees";
    }

    @GetMapping("/feesCreate")
    public String feesCreate() {
        return "fees/fees-create";
    }

    @GetMapping("/feesUpdate/{feesId}")
    public String feesUpdate(@PathVariable Long feesId, Model model) {
        FeesResponseDto dto = feesService.findById(feesId);
        model.addAttribute("fees", dto);
        model.addAttribute("users", memberService.findAllDesc());
        return "fees/fees-update";
    }


}
