package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.dto.student.CreateStudentDto;
import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.service.StudentService;
import com.informatics.e_school_journal.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
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
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

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
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        mapperConfig.getModelMapper().map(updateStudentDto, existingStudent);

        Student updatedStudent = this.studentRepository.save(existingStudent);

        return mapperConfig.getModelMapper().map(updatedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

}