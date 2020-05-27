package com.jean.blue.repository;

import com.jean.blue.domain.Board;
import com.jean.blue.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT p FROM Board p WHERE p.isDeleted = 0 ORDER BY p.boardId DESC")
    List<File> findAllDesc();

    void deleteByBoard(Board board);
}
