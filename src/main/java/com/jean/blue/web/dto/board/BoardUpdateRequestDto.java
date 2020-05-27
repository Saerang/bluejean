package com.jean.blue.web.dto.board;

import com.jean.blue.domain.enumeration.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private BoardType boardType;
    private String title;
    private String content;
    private boolean isDeleted;

    @Builder
    public BoardUpdateRequestDto(BoardType boardType, String title, String content, Boolean isDeleted) {
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
    }

}
