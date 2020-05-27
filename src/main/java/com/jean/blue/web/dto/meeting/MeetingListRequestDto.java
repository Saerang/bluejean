package com.jean.blue.web.dto.meeting;

import com.jean.blue.domain.enumeration.MeetingType;
import com.jean.blue.domain.Meeting;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MeetingListRequestDto {
    private Long meetingId;
    private MeetingType type;
    private int meetingOrder;
    private String title;
    private String content;
    private String place;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public MeetingListRequestDto(Meeting entity){
        this.meetingId = entity.getMeetingId();
        this.type = entity.getType();
        this.meetingOrder = entity.getMeetingOrder();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.place = entity.getPlace();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
