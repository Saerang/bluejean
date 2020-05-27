package com.jean.blue.repository;

import com.jean.blue.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query("SELECT p FROM Meeting p WHERE p.isDeleted = false ORDER BY p.meetingId DESC")
    List<Meeting> findAllDesc();
}
