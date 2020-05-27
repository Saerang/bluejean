package com.jean.blue.service;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.File;
import com.jean.blue.domain.enumeration.BoardType;
import com.jean.blue.repository.BoardRepository;
import com.jean.blue.repository.FileRepository;
import com.jean.blue.util.S3Upload;
import com.jean.blue.util.S3Uploader;
import com.jean.blue.web.dto.board.BoardListRequestDto;
import com.jean.blue.web.dto.board.BoardResponseDto;
import com.jean.blue.web.dto.board.BoardSaveRequestDto;
import com.jean.blue.web.dto.board.BoardUpdateRequestDto;
import com.jean.blue.web.dto.file.FileSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    private final FileService fileService;

    private final S3Upload s3Upload;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto, List<MultipartFile> files) throws IOException {

        Board board = boardRepository.save(requestDto.toEntity());
        if(files.size() > 0){
            for(MultipartFile file : files){
                String fileUrl = s3Upload.upload(file, "board-image");
                fileRepository.save(FileSaveRequestDto.builder()
                        .board(board)
                        .fileUrl(fileUrl)
                        .fileName(file.getName())
                        .originFileName(file.getOriginalFilename())
                        .fileSize(file.getSize())
                        .build().toEntity());
            }
        }

        return board.getBoardId();
    }

    @Transactional
    public Long update(Long board_id, BoardUpdateRequestDto requestDto, List<MultipartFile> files) throws IOException {
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + board_id));
        board.update(requestDto.getBoardType(), requestDto.getTitle(), requestDto.getContent());

        fileRepository.deleteByBoard(board);

        if(files.size() > 0){
            for(MultipartFile file : files){
                String fileUrl = s3Upload.upload(file, "board-image");
                fileRepository.save(FileSaveRequestDto.builder()
                        .board(board)
                        .fileUrl(fileUrl)
                        .fileName(file.getName())
                        .originFileName(file.getOriginalFilename())
                        .fileSize(file.getSize())
                        .build().toEntity());
            }
        }

        return board_id;
    }

    @Transactional
    public Long delete(Long id, BoardUpdateRequestDto requestDto) {
        Board fees = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + id));
        fees.delete(requestDto.isDeleted());
        return id;
    }

    public BoardResponseDto findById(Long id){
        Board entity = boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new BoardResponseDto(entity);
    }

    public BoardResponseDto findByIdWithFetchJoin(Long id) {
        Board entity = boardRepository.findByIdWithFetchJoin(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new BoardResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardListRequestDto> findAllByBoardType(){
        return boardRepository.findAllByBoardType(BoardType.CHAT).stream()
                .map(BoardListRequestDto::new)
                .collect(Collectors.toList());
    }

}
