package com.jean.blue.domain.enumeration;

import com.jean.blue.domain.enumeration.mapper.EnumMapperType;

public enum BoardType implements EnumMapperType {
    NOTICE("공지"),
    CHAT("잡담");

    private String boardType;

    BoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBoardType(){
        return boardType;
    }

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getText() {
        return boardType;
    }
}
