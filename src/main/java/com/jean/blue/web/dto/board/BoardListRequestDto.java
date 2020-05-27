package com.jean.blue.web.dto.board;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.enumeration.BoardType;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardListRequestDto {
    private Long boardId;
    private Member member;
    private BoardType boardType;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public BoardListRequestDto(Board entity){
        this.boardId = entity.getBoardId();
        this.member = entity.getMember();
        this.boardType = entity.getBoardType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
