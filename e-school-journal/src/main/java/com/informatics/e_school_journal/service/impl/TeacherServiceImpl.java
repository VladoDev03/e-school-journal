package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.dto.teacher.CreateTeacherDto;
import com.informatics.e_school_journal.dto.teacher.TeacherDto;
import com.informatics.e_school_journal.dto.teacher.TeacherPersonalInfoDto;
import com.informatics.e_school_journal.dto.teacher.UpdateTeacherDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.exception.EntityNotFoundException;
import com.informatics.e_school_journal.service.SchoolService;
import com.informatics.e_school_journal.service.TeacherService;
import com.informatics.e_school_journal.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapperConfig mapperConfig;

    private final UserService userService;
    private final SchoolService schoolService;

    @Override
    @Transactional
    public TeacherDto createTeacher(CreateTeacherDto createTeacherDto) {
        userService.registerUser(createTeacherDto.getCreateUserDto());
        UserDto userDto = userService.getUserByEmail(createTeacherDto.getCreateUserDto().getEmail());

        RoleDto roleDto = userService.getRoleByName("teacher");
        userService.setRole(userDto.getId(), roleDto);

        Teacher teacher = mapperConfig.getModelMapper().map(createTeacherDto, Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return mapperConfig.getModelMapper().map(savedTeacher, TeacherDto.class);
    }

    @Override
    public TeacherDto getTeacherById(String id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));

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
    public TeacherDto updateTeacher(String id, UpdateTeacherDto updateTeacherDto) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));

        mapperConfig.getModelMapper().map(updateTeacherDto, existingTeacher);
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);

        return mapperConfig.getModelMapper().map(updatedTeacher, TeacherDto.class);
    }

    @Override
    public void deleteTeacher(String id) {
        if (!teacherRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher not found with id: " + id);
        }

        teacherRepository.deleteById(id);
    }

    @Transactional
    @Override
    public TeacherDto createTeacherRole(String userId) {
        if (userService.getUserPossibleRoles(userId).stream().noneMatch(role -> role.getName().equals("teacher"))) {
            throw new IllegalArgumentException("User cannot be assigned this role.");
        }

        RoleDto roleDto = userService.getRoleByName("teacher");
        userService.setRole(userId, roleDto);

        Teacher teacher = new Teacher(userId);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return mapperConfig.getModelMapper().map(savedTeacher, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> getTeachersInSchool(String schoolId) {
        SchoolDto schoolDto = schoolService.getSchoolById(schoolId);

        return this.teacherRepository.findTeachersByStudyingsGradeSchoolId(schoolId)
                .stream()
                .map(teacher -> mapperConfig.getModelMapper().map(teacher, TeacherDto.class))
                .toList();
    }

    @Override
    public List<TeacherPersonalInfoDto> getTeachersByDirector() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return this.teacherRepository.findTeachersByStudyingsGradeSchoolDirectorId(authentication.getName())
                .stream().map(teacher ->
                        {
                            UserDto userDto = userService.getUserById(teacher.getId());
                            return new TeacherPersonalInfoDto(
                                    userDto.getId(),
                                    userDto.getFirstName(),
                                    userDto.getLastName());
                        })
                .toList();
    }

    /*
    UserDto userDto = userService.getUserById(student.getId());
                    return new StudentPersonalInfoDto(
                            userDto.getId(),
                            userDto.getFirstName(),
                            userDto.getLastName()
                            */
}
