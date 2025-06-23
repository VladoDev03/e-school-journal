package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findMarksByStudentId(Long studentId);
}
