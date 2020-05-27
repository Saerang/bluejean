package com.jean.blue.service;

import com.jean.blue.domain.Meeting;
import com.jean.blue.repository.MeetingRepository;
import com.jean.blue.web.dto.meeting.MeetingListRequestDto;
import com.jean.blue.web.dto.meeting.MeetingResponseDto;
import com.jean.blue.web.dto.meeting.MeetingSaveRequestDto;
import com.jean.blue.web.dto.meeting.MeetingUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    @Transactional
    public Long save(MeetingSaveRequestDto requestDto) {
        return meetingRepository.save(requestDto.toEntity()).getMeetingId();
    }

    @Transactional
    public Long update(Long id, MeetingUpdateRequestDto requestDto) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + id));
        meeting.update(requestDto.getType(), requestDto.getMeetingOrder(), requestDto.getTitle(), requestDto.getContent(), requestDto.getPlace());
        return id;
    }

    @Transactional
    public Long delete(Long id, MeetingUpdateRequestDto requestDto) {
        Meeting fees = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + id));
        fees.delete(requestDto.isDeleted());
        return id;
    }

    public MeetingResponseDto findById(Long id){
        Meeting entity = meetingRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new MeetingResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<MeetingListRequestDto> findAllDesc(){
        return meetingRepository.findAllDesc().stream()
                .map(MeetingListRequestDto::new)
                .collect(Collectors.toList());
    }

}
