package com.jean.blue.service;

import com.jean.blue.domain.File;
import com.jean.blue.repository.FileRepository;
import com.jean.blue.util.S3Uploader;
import com.jean.blue.web.dto.file.FileListRequestDto;
import com.jean.blue.web.dto.file.FileResponseDto;
import com.jean.blue.web.dto.file.FileSaveRequestDto;
import com.jean.blue.web.dto.file.FileUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    private final S3Uploader s3Uploader;

    @Transactional
    public Long save(FileSaveRequestDto requestDto) throws IOException {

        return fileRepository.save(requestDto.toEntity()).getFileId();
    }

    @Transactional
    public Long update(Long id, FileUpdateRequestDto requestDto) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + id));
        //file.update(requestDto.getFileType(), requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public Long delete(Long id, FileUpdateRequestDto requestDto) {
        File fees = fileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + id));
        //fees.delete(requestDto.isDeleted());
        return id;
    }

    public FileResponseDto findById(Long id){
        File entity = fileRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new FileResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<FileListRequestDto> findAllDesc(){
        return fileRepository.findAllDesc().stream()
                .map(FileListRequestDto::new)
                .collect(Collectors.toList());
    }



}
