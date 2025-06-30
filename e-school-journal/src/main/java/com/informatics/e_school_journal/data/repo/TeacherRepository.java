package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    List<Teacher> findTeachersByStudyingsGradeSchoolId(String schoolId);
    List<Teacher> findTeachersByStudyingsGradeSchoolDirectorId(String directorId);
}

