package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.data.entity.Teacher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherService {
    Mono<Teacher> createTeacher(Teacher teacher);
    Mono<Teacher> getTeacherById(long id);
    Flux<Teacher> getTeachers();
    Mono<Teacher> updateTeacher(Long id, Teacher teacher);
    Mono<Void> deleteTeacher(long id);
}
