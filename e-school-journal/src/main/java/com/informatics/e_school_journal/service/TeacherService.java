package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.TeacherDto.CreateTeacherDto;
import com.informatics.e_school_journal.dto.TeacherDto.TeacherDto;
import com.informatics.e_school_journal.dto.TeacherDto.UpdateTeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getTeachers();
    TeacherDto getTeacherById(long id);
    TeacherDto createTeacher(CreateTeacherDto createTeacherDto);
    TeacherDto updateTeacher(long id, UpdateTeacherDto updateTeacherDto);
    void deleteTeacher(long id);
}
