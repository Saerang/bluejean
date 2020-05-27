package com.jean.blue.repository;

import com.jean.blue.domain.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeesRepository extends JpaRepository<Fees, Long> {

    @Query("SELECT p FROM Fees p WHERE p.isDeleted = 0 ORDER BY p.feesId DESC")
    List<Fees> findAllDesc();
}
