package com.jean.blue.web.api;

import com.jean.blue.domain.enumeration.BoardType;
import com.jean.blue.domain.enumeration.mapper.EnumMapperValue;
import com.jean.blue.domain.Member;
import com.jean.blue.service.BoardService;
import com.jean.blue.service.FileService;
import com.jean.blue.service.MemberService;
import com.jean.blue.util.S3Upload;
import com.jean.blue.util.S3Uploader;
import com.jean.blue.web.dto.board.BoardListRequestDto;
import com.jean.blue.web.dto.board.BoardResponseDto;
import com.jean.blue.web.dto.board.BoardSaveRequestDto;
import com.jean.blue.web.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardApiController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final FileService fileService;


    @PostMapping(value = "/board", consumes = {"multipart/form-data"})
    public Long save(@RequestParam List<MultipartFile> files, @RequestParam String title,  Principal principal) throws IOException {
        Member member = memberService.findMemberByUserId(principal.getName());
        BoardSaveRequestDto boardSaveRequestDto = BoardSaveRequestDto.builder().boardType(BoardType.CHAT)
                                                                        .title(title)
                                                                        .member(member).build();

        return boardService.save(boardSaveRequestDto, files);
    }

    @PostMapping("/board/update/{id}")
    public Long update(@PathVariable Long id, @RequestParam List<MultipartFile> files, @RequestParam String title) throws IOException {
        BoardUpdateRequestDto boardUpdateRequestDto = BoardUpdateRequestDto.builder().boardType(BoardType.CHAT).isDeleted(false)
                                                                            .title(title).build();
        return boardService.update(id, boardUpdateRequestDto, files);
    }

    @PutMapping("/board/delete/{id}")
    public Long delete(@PathVariable Long id,  @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.delete(id, requestDto);
    }

    @GetMapping("/board")
    public List<BoardListRequestDto> board() {
        List<BoardListRequestDto> boardListRequestDto = boardService.findAllByBoardType();
        return boardListRequestDto;
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @GetMapping("/board/type")
    public List<EnumMapperValue> memberRole(){
        return Arrays.stream(BoardType.values())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

}
