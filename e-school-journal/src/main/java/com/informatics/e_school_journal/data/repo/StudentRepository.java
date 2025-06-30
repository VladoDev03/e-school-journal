package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findStudentsByParentsId(String parentId);
    List<Student> findStudentsByGradeSchoolDirectorId(String directorId);
}
