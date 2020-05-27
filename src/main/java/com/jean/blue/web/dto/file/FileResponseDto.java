package com.jean.blue.web.dto.file;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.enumeration.FileType;
import com.jean.blue.domain.File;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileResponseDto {
    private Long fileId;
    private Board board;
    private String fileUrl;
    private String fileName;
    private String originFileName;
    private FileType fileType;
    private long fileSize;

    @Builder
    public FileResponseDto(File entity){
        this.fileId = entity.getFileId();
        this.board = entity.getBoard();
        this.fileUrl = entity.getFileUrl();
        this.fileName = entity.getFileName();
        this.originFileName = entity.getOriginFileName();
        this.fileType = entity.getFileType();
        this.fileSize = entity.getFileSize();
    }
}
