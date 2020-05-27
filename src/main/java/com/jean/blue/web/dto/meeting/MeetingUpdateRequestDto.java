package com.jean.blue.web.dto.meeting;

import com.jean.blue.domain.enumeration.MeetingType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MeetingUpdateRequestDto {
    private MeetingType type;
    private int meetingOrder;
    private String title;
    private String content;
    private String place;
    private boolean isDeleted;

    @Builder
    public MeetingUpdateRequestDto(MeetingType type, int meetingOrder, String title, String content, String place, boolean isDeleted) {
        this.type = type;
        this.meetingOrder = meetingOrder;
        this.title = title;
        this.content = content;
        this.place = place;
        this.isDeleted = isDeleted;
    }

}
