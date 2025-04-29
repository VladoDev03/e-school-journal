package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public Flux<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Mono<Student> updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping
    public Mono<Void> deleteStudent(@PathVariable Student student) {
        return studentService.deleteStudent(student.getId());
    }
}
