package com.jean.blue.web.dto.meeting;

import com.jean.blue.domain.enumeration.MeetingType;
import com.jean.blue.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MeetingResponseDto {
    private Long meetingId;
    private MeetingType type;
    private int meetingOrder;
    private String title;
    private String content;
    private String place;

    @Builder
    public MeetingResponseDto(Meeting entity){
        this.meetingId = entity.getMeetingId();
        this.type = entity.getType();
        this.meetingOrder = entity.getMeetingOrder();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.place = entity.getPlace();
    }
}
