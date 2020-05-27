package com.jean.blue.web.dto.file;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.enumeration.FileType;
import com.jean.blue.domain.File;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileSaveRequestDto {
    private Board board;
    private String fileUrl;
    private String fileName;
    private String originFileName;
    private FileType fileType;
    private long fileSize;
    private boolean isDeleted;

    @Builder
    public FileSaveRequestDto(Board board, String fileUrl, String fileName, String originFileName, FileType fileType, long fileSize) {
        this.board = board;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.isDeleted = false;
    }

    public File toEntity() {
        return File.builder()
                .board(board)
                .fileUrl(fileUrl)
                .fileName(fileName)
                .originFileName(originFileName)
                .fileType(fileType)
                .fileSize(fileSize)
                .build();
    }

}
