package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findGradesBySchoolId(Long schoolId);
}
