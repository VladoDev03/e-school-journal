package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Parent;
import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.repo.ParentRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.dto.student.CreateStudentDto;
import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentDto;
import com.informatics.e_school_journal.dto.user.CreateUserDto;
import com.informatics.e_school_journal.dto.user.CredentialsDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.exception.EntityNotFoundException;
import com.informatics.e_school_journal.service.UserService;
import com.informatics.e_school_journal.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock private StudentRepository studentRepository;
    @Mock private ParentRepository parentRepository;
    @Mock private UserService userService;
    @Mock private ModelMapperConfig mapperConfig;

    @InjectMocks
    private StudentServiceImpl studentService;

    private ModelMapper modelMapper;

    @Test
    void getStudentById_shouldReturnMappedStudentDto() {
        modelMapper = new ModelMapper();
        given(mapperConfig.getModelMapper()).willReturn(modelMapper);

        Student student = new Student("123");

        given(studentRepository.findById("123")).willReturn(Optional.of(student));

        StudentDto result = studentService.getStudentById("123");

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
    }

    @Test
    void getStudentById_shouldThrow_whenStudentNotFound() {
        given(studentRepository.findById("missing")).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            studentService.getStudentById("missing");
        });
    }

}
