package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.teacher.CreateTeacherDto;
import com.informatics.e_school_journal.dto.teacher.TeacherDto;
import com.informatics.e_school_journal.dto.teacher.UpdateTeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getTeachers();
    TeacherDto getTeacherById(String id);
    TeacherDto createTeacher(CreateTeacherDto createTeacherDto);
    TeacherDto updateTeacher(String id, UpdateTeacherDto updateTeacherDto);
    void deleteTeacher(String id);
    List<TeacherDto> getTeachersInSchool(String schoolId);
}
