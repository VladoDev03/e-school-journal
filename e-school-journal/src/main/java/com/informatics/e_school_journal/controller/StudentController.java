package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableReactiveMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public Flux<Student> getStudents() {
        return studentService.getStudents();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public Mono<Student> updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student.getId(), student);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }
}
