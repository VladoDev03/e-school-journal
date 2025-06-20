package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.student.CreateStudentDto;
import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getStudents();
    StudentDto getStudentById(long id);
    StudentDto createStudent(CreateStudentDto createStudentDto);
    StudentDto updateStudent(long id, UpdateStudentDto updateStudentDto);
    void deleteStudent(long id);
}
