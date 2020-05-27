package com.jean.blue.web;

import com.jean.blue.domain.File;
import com.jean.blue.domain.enumeration.BoardType;
import com.jean.blue.domain.enumeration.Role;
import com.jean.blue.domain.enumeration.mapper.EnumMapperValue;
import com.jean.blue.service.BoardService;
import com.jean.blue.web.dto.board.BoardResponseDto;
import com.jean.blue.web.dto.fees.FeesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String member(Model model){
        model.addAttribute("boards", boardService.findAllByBoardType());
        return "board/boards";
    }

    @GetMapping("/boardDetail/{boardId}")
    public String boardDetail(@PathVariable Long boardId, Model model){
        BoardResponseDto board = boardService.findByIdWithFetchJoin(boardId);
        model.addAttribute("board", board);
        return "board/board-detail";
    }

    @GetMapping("/boardCreate")
    public String boardCreate(Model model) {
        model.addAttribute("boardType", Arrays.stream(BoardType.values())
                                                        .map(EnumMapperValue::new)
                                                        .collect(Collectors.toList()));
        return "board/board-create";
    }

    @GetMapping("/boardUpdate/{id}")
    public String boardUpdate(@PathVariable Long id, Model model) {
        BoardResponseDto dto = boardService.findByIdWithFetchJoin(id);
        model.addAttribute("board", dto);
        return "board/board-update";
    }


}
