package com.jean.blue.web.dto.board;

import com.jean.blue.domain.enumeration.BoardType;
import com.jean.blue.domain.Board;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private Member member;
    private BoardType boardType;
    private String title;
    private String content;
    private List<MultipartFile> files;

    @Builder
    public BoardSaveRequestDto(Member member, BoardType boardType, String title, String content, List<MultipartFile> files) {
        this.member = member;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.files = files;
    }

    public Board toEntity() {
        return Board.builder()
                .member(member)
                .boardType(boardType)
                .title(title)
                .content(content)
                .build();
    }

}
