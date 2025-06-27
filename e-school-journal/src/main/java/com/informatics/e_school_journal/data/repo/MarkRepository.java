package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findMarksByStudentId(Long studentId);
    boolean existsByStudentIdAndStudyingIdAndMarkTypeAndTerm(Long studentId, Long studyingId, MarkType markType, Term term);
}
