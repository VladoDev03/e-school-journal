package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.dto.teacher.CreateTeacherDto;
import com.informatics.e_school_journal.dto.teacher.TeacherDto;
import com.informatics.e_school_journal.dto.teacher.UpdateTeacherDto;
import com.informatics.e_school_journal.service.SchoolService;
import com.informatics.e_school_journal.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapperConfig mapperConfig;

    private final SchoolService schoolService;

    @Override
    public TeacherDto createTeacher(CreateTeacherDto createTeacherDto) {
        Teacher teacher = mapperConfig.getModelMapper().map(createTeacherDto, Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return mapperConfig.getModelMapper().map(savedTeacher, TeacherDto.class);
    }

    @Override
    public TeacherDto getTeacherById(long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        return mapperConfig.getModelMapper().map(teacher, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> getTeachers() {
        return this.teacherRepository.findAll()
                .stream()
                .map(teacher -> this.mapperConfig
                        .getModelMapper()
                        .map(teacher, TeacherDto.class))
                .toList();
    }

    @Override
    public TeacherDto updateTeacher(long id, UpdateTeacherDto updateTeacherDto) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        mapperConfig.getModelMapper().map(updateTeacherDto, existingTeacher);
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);

        return mapperConfig.getModelMapper().map(updatedTeacher, TeacherDto.class);
    }

    @Override
    public void deleteTeacher(long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDto> getTeachersInSchool(Long schoolId) {

        SchoolDto schoolDto = schoolService.getSchoolById(schoolId);

        return this.teacherRepository.findTeachersByStudyingsGradeSchoolId(schoolId)
                .stream()
                .map(teacher -> mapperConfig.getModelMapper().map(teacher, TeacherDto.class))
                .toList();

    }
}
