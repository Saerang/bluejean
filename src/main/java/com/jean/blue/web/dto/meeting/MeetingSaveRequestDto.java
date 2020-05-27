package com.jean.blue.web.dto.meeting;

import com.jean.blue.domain.enumeration.MeetingType;
import com.jean.blue.domain.Meeting;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MeetingSaveRequestDto {
    private MeetingType type;
    private int meetingOrder;
    private String title;
    private String content;
    private String place;

    @Builder
    public MeetingSaveRequestDto(MeetingType type, int meetingOrder, String title, String content, String place) {
        this.type = type;
        this.meetingOrder = meetingOrder;
        this.title = title;
        this.content = content;
        this.place = place;
    }

    public Meeting toEntity() {
        return Meeting.builder()
                .type(type)
                .meetingOrder(meetingOrder)
                .title(title)
                .content(content)
                .place(place)
                .build();
    }

}
