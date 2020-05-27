package com.jean.blue.repository;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.enumeration.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b JOIN FETCH b.member m LEFT JOIN FETCH b.file f WHERE b.isDeleted = false AND b.boardType = 'CHAT' AND b.boardId= :board_id ORDER BY b.boardId DESC")
    Optional<Board> findByIdWithFetchJoin(Long board_id);

    @Query("SELECT b FROM Board b JOIN FETCH b.member WHERE b.isDeleted = false AND b.boardType = :boardType ORDER BY b.boardId DESC")
    List<Board> findAllByBoardType(BoardType boardType);
}
