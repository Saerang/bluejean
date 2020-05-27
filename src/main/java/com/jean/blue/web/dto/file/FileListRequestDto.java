package com.jean.blue.web.dto.file;

import com.jean.blue.domain.File;
import com.jean.blue.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FileListRequestDto {
    private Long fileId;
    private String fileUrl;
    private String fileName;
    private String fileOriginFileName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public FileListRequestDto(File entity){
        this.fileId = entity.getFileId();
        this.fileUrl = entity.getFileUrl();
        this.fileName = entity.getFileName();
        this.fileOriginFileName = entity.getOriginFileName();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
