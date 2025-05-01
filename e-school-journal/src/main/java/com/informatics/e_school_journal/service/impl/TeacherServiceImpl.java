package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.TeacherDto.CreateTeacherDto;
import com.informatics.e_school_journal.dto.TeacherDto.TeacherDto;
import com.informatics.e_school_journal.dto.TeacherDto.UpdateTeacherDto;
import com.informatics.e_school_journal.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public Mono<TeacherDto> createTeacher(CreateTeacherDto createTeacherDto) {
        Teacher teacher = mapperConfig.getModelMapper().map(createTeacherDto, Teacher.class);
        return this.teacherRepository.save(teacher)
                .map(savedTeacher -> mapperConfig.getModelMapper().map(savedTeacher, TeacherDto.class));
    }

    @Override
    public Mono<TeacherDto> getTeacherById(long id) {
        return this.teacherRepository.findById(id)
                .map(teacher -> this.mapperConfig
                        .getModelMapper()
                        .map(teacher, TeacherDto.class));
    }

    @Override
    public Flux<TeacherDto> getTeachers() {
        return this.teacherRepository.findAll()
                .map(teacher -> this.mapperConfig
                        .getModelMapper()
                        .map(teacher, TeacherDto.class));
    }

    @Override
    public Mono<TeacherDto> updateTeacher(Long id, UpdateTeacherDto updatedTeacherDto) {
        return this.teacherRepository.findById(id)
                .flatMap(existingTeacher -> {
                    mapperConfig.getModelMapper().map(updatedTeacherDto, existingTeacher);
                    return this.teacherRepository.save(existingTeacher);
                })
                .switchIfEmpty(Mono.error(new Exception("Teacher not found with id: " + id)))
                .map(updatedTeacher -> mapperConfig.getModelMapper().map(updatedTeacher, TeacherDto.class));
    }

    @Override
    public Mono<Void> deleteTeacher(long id) {
        return teacherRepository.deleteById(id);
    }
}
