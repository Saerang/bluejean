package com.jean.blue.web.api;

import com.jean.blue.domain.enumeration.MeetingType;
import com.jean.blue.domain.enumeration.mapper.EnumMapperValue;
import com.jean.blue.service.MeetingService;
import com.jean.blue.web.dto.meeting.MeetingListRequestDto;
import com.jean.blue.web.dto.meeting.MeetingResponseDto;
import com.jean.blue.web.dto.meeting.MeetingUpdateRequestDto;
import com.jean.blue.web.dto.meeting.MeetingSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MeetingApiController {

    private final MeetingService meetingService;

    @PostMapping("/meeting")
    public Long save(@RequestBody MeetingSaveRequestDto requestDto){
        Long returnId = meetingService.save(requestDto);
        return returnId;
    }

    @PutMapping("/meeting/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody MeetingUpdateRequestDto requestDto) {
        return meetingService.update(id, requestDto);
    }

    @PutMapping("/meeting/delete/{id}")
    public Long delete(@PathVariable Long id,  @RequestBody MeetingUpdateRequestDto requestDto) {
        return meetingService.delete(id, requestDto);
    }

    @GetMapping("/meeting")
    public List<MeetingListRequestDto> meeting() {
        List<MeetingListRequestDto> meetingListRequestDto = meetingService.findAllDesc();
        return meetingListRequestDto;
    }

    @GetMapping("/meeting/{id}")
    public MeetingResponseDto findById(@PathVariable Long id) {
        return meetingService.findById(id);
    }

    @GetMapping("/meeting/type")
    public List<EnumMapperValue> memberRole(){
        return Arrays.stream(MeetingType.values())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

}
