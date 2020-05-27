package com.jean.blue.domain;

import com.jean.blue.domain.enumeration.FileType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Column(nullable = false)
    private long fileSize;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    private int isDeleted;

    @Builder
    public File(Long fileId, Board board, String fileUrl, String fileName, String originFileName, FileType fileType, long fileSize) {
        this.fileId = fileId;
        this.board = board;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.isDeleted = 0;
    }

    public void update(String fileUrl, String fileName, String originFileName, FileType fileType, long fileSize){
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public void delete(int isDeleted){
        this.isDeleted = isDeleted;
    }

}
