package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Mono<Teacher> createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Mono<Teacher> getTeacherById(long id) {
        return teacherRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Teacher not found!")));
    }

    @Override
    public Flux<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Mono<Teacher> updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id)
                .flatMap(existing -> {
                    existing.setKeycloakId(updatedTeacher.getKeycloakId());
                    return teacherRepository.save(existing);
                });
    }


    @Override
    public Mono<Void> deleteTeacher(long id) {
        return teacherRepository.deleteById(id);
    }
}
