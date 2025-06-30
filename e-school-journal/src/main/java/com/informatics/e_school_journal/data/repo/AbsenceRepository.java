package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, String> {
    List<Absence> findAbsencesByStudentId(String studentId);

    @Query("""
    SELECT sc.name,
    sb.name,
    COUNT (a.id)
    FROM Absence a
    JOIN a.studying s
    JOIN s.subject sb
    JOIN s.grade g
    JOIN g.school sc
    GROUP BY sb.name, sc.name
    """)
    List<Object[]> getCountAbsencesBySchoolAndSubject();
}
