package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findTeachersByStudyingsGradeSchoolId(Long schoolId);
}

