package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, String> {
    List<Mark> findMarksByStudentId(String studentId);
    boolean existsByStudentIdAndStudyingIdAndMarkTypeAndTerm(String studentId, String studyingId, MarkType markType, Term term);

    @Query("""
    SELECT s.teacher.id,
    AVG (m.mark)
    FROM Mark m
    JOIN m.studying s
    JOIN s.grade g
    JOIN g.school sc
    JOIN sc.director d
    WHERE d.id = :directorId
    GROUP BY s.teacher.id
    """)
    List<Object[]> getAvgMarksByTeacherByDirector(@Param("directorId") String directorId);

    @Query("""
    SELECT sb.name,
    AVG (m.mark)
    FROM Mark m
    JOIN m.studying s
    JOIN s.subject sb
    JOIN s.grade g
    JOIN g.school sc
    JOIN sc.director d
    WHERE d.id = :directorId
    GROUP BY sb.name
    """)
    List<Object[]> getAvgMarksBySubjectByDirector(@Param("directorId") String directorId);

    @Query("""
    SELECT sc.name,
    AVG (m.mark)
    FROM Mark m
    JOIN m.studying s
    JOIN s.subject sb
    JOIN s.grade g
    JOIN g.school sc
    JOIN sc.director d
    WHERE d.id = :directorId
    """)
    List<Object[]> getAvgMarksByDirector(@Param("directorId") String directorId);

    @Query("""
    SELECT sc.name,
    AVG (m.mark)
    FROM Mark m
    JOIN m.studying s
    JOIN s.subject sb
    JOIN s.grade g
    JOIN g.school sc
    WHERE sc.id = :schoolId
    GROUP BY sb.name
    """)
    List<Object[]> getAvgMarksBySchool(@Param("schoolId") String schoolId);
}
