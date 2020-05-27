package com.jean.blue.domain;

import com.jean.blue.domain.enumeration.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    private String title;

    private String content;

    private boolean isDeleted;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="board_id")
    private List<File> file;

    @Builder
    public Board(BoardType boardType, String title, String content, Member member) {
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(BoardType boardType, String title, String content){
        this.boardType = boardType;
        this.title = title;
        this.content = content;
    }

    public void delete(boolean isDeleted){
        this.isDeleted = isDeleted;
    }



}
