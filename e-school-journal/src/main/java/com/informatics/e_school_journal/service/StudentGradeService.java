package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.StudentInGradeDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentInGradeDto;

public interface StudentGradeService {
    StudentInGradeDto enrollStudentInGrade(Long studentId, Long gradeId);
    StudentInGradeDto withdrawStudentFromGrade(Long studentId);

    StudentInGradeDto updateStudentInGrade(Long studentId, UpdateStudentInGradeDto updateStudentInGradeDto);

}
