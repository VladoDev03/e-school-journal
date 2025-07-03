package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Parent;
import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.repo.ParentRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.dto.student.CreateStudentDto;
import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.StudentPersonalInfoDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.exception.EntityNotFoundException;
import com.informatics.e_school_journal.exception.UserNotAuthorizedException;
import com.informatics.e_school_journal.service.StudentService;
import com.informatics.e_school_journal.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    private final ModelMapperConfig mapperConfig;

    private final UserService userService;

    @Override
    public List<StudentDto> getStudents() {
        return this.studentRepository.findAll()
                .stream()
                .map(student -> this.mapperConfig
                        .getModelMapper()
                        .map(student, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(String id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        return this.mapperConfig.getModelMapper().map(student, StudentDto.class);
    }

    @Override
    @Transactional
    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        userService.registerUser(createStudentDto.getCreateUserDto());
        UserDto userDto = userService.getUserByEmail(createStudentDto.getCreateUserDto().getEmail());

        RoleDto roleDto = userService.getRoleByName("student");
        userService.setRole(userDto.getId(), roleDto);

        Student student = new Student(userDto.getId());
        Student savedStudent = this.studentRepository.save(student);

        return mapperConfig.getModelMapper().map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(String id, UpdateStudentDto updateStudentDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        mapperConfig.getModelMapper().map(updateStudentDto, existingStudent);

        Student updatedStudent = this.studentRepository.save(existingStudent);

        return mapperConfig.getModelMapper().map(updatedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentPersonalInfoDto> getStudentsByParentId(String parentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication
                .getAuthorities()
                .stream()
                .anyMatch(x -> x.getAuthority().equals("admin"));

        if(authentication.getName() != parentId && !isAdmin) {
            throw new UserNotAuthorizedException("User not authorized for this action.");
        }

        Parent parent = this.parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with id: " + parentId));

        List<Student> students = this.studentRepository.findStudentsByParentsId(parentId);
        return students
                .stream()
                .map(student -> {
                    UserDto userDto = userService.getUserById(student.getId());
                    return new StudentPersonalInfoDto(
                            userDto.getId(),
                            userDto.getFirstName(),
                            userDto.getLastName()
                    );
                })
                .toList();
    }

    @Override
    public List<StudentPersonalInfoDto> getStudentsByDirectorId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return this.studentRepository.findStudentsByGradeSchoolDirectorId(authentication.getName())
                .stream()
                .map(student -> {
                    UserDto userDto = userService.getUserById(student.getId());
                    return new StudentPersonalInfoDto(
                            userDto.getId(),
                            userDto.getFirstName(),
                            userDto.getLastName()
                    );
                })
                .toList();
    }
}