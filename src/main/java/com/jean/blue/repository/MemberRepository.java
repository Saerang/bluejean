package com.jean.blue.repository;

import com.jean.blue.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT p FROM Member p WHERE p.isDeleted = 0 ORDER BY p.memberId DESC")
    List<Member> findAllDesc();

    @Query("SELECT p FROM Member p WHERE p.isDeleted = 0 AND p.userId = :userId ORDER BY p.memberId DESC")
    Member findByUserId(@Param("userId") String userId);

}
