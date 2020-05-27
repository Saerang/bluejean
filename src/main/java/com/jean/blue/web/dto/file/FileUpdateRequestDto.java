package com.jean.blue.web.dto.file;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.enumeration.FileType;
import com.jean.blue.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileUpdateRequestDto {
    private Board board;
    private String fileUrl;
    private String fileName;
    private String originFileName;
    private FileType fileType;
    private int fileSize;
    private boolean isDeleted;

    public FileUpdateRequestDto(Board board, String fileUrl, String fileName, String originFileName, FileType fileType, int fileSize, boolean isDeleted) {
        this.board = board;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.originFileName = originFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.isDeleted = isDeleted;
    }
}
