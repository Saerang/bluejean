package com.jean.blue.domain.enumeration;

import com.jean.blue.domain.enumeration.mapper.EnumMapperType;

public enum MeetingType implements EnumMapperType {
    REGULAR("정기모임"),
    INSTANT("즉석모임");

    private String meetingType;

    MeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingType(){
        return meetingType;
    }

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getText() {
        return meetingType;
    }
}
