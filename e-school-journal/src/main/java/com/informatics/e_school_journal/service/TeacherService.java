package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.TeacherDto.CreateTeacherDto;
import com.informatics.e_school_journal.dto.TeacherDto.TeacherDto;
import com.informatics.e_school_journal.dto.TeacherDto.UpdateTeacherDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherService {
    Mono<TeacherDto> createTeacher(CreateTeacherDto createTeacherDto);
    Mono<TeacherDto> getTeacherById(long id);
    Flux<TeacherDto> getTeachers();
    Mono<TeacherDto> updateTeacher(Long id, UpdateTeacherDto updateTeacherDto);
    Mono<Void> deleteTeacher(long id);
}
