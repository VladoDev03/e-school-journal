package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.StudentInGradeDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentInGradeDto;

public interface StudentGradeService {
    StudentInGradeDto enrollStudentInGrade(String studentId, String gradeId);
    StudentInGradeDto withdrawStudentFromGrade(String studentId);

    StudentInGradeDto updateStudentInGrade(String studentId, UpdateStudentInGradeDto updateStudentInGradeDto);

}
