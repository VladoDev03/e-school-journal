package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.student.CreateStudentDto;
import com.informatics.e_school_journal.dto.student.StudentDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentDto;
import com.informatics.e_school_journal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<StudentDto> getStudents() {
        return studentService.getStudents();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public StudentDto createStudent(@RequestBody CreateStudentDto student) {
        return studentService.createStudent(student);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public StudentDto updateStudent(@PathVariable long id, @RequestBody UpdateStudentDto student) {
        return studentService.updateStudent(id, student);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
    }
}