package com.jean.blue.web.dto.board;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.File;
import com.jean.blue.domain.enumeration.BoardType;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto {
    private Long boardId;
    private Member member;
    private BoardType boardType;
    private String title;
    private String content;
    private List<File> files;

    @Builder
    public BoardResponseDto(Board entity){
        this.boardId = entity.getBoardId();
        this.member = entity.getMember();
        this.boardType = entity.getBoardType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.files = entity.getFile();
    }
}
