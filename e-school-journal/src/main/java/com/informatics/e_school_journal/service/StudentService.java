package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.student.CreateStudentDto;
import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getStudents();
    StudentDto getStudentById(String id);
    StudentDto createStudent(CreateStudentDto createStudentDto);
    StudentDto updateStudent(String id, UpdateStudentDto updateStudentDto);
    void deleteStudent(String id);

}
