//package com.informatics.e_school_journal.controller;
//
//import com.informatics.e_school_journal.dto.student.CreateStudentDto;
//import com.informatics.e_school_journal.dto.student.StudentDto;
//import com.informatics.e_school_journal.dto.student.UpdateStudentDto;
//import com.informatics.e_school_journal.service.StudentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@EnableReactiveMethodSecurity
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/students")
//public class StudentController {
//    private final StudentService studentService;
//
//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping
//    public Flux<StudentDto> getStudents() {
//        return studentService.getStudents();
//    }
//
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
//    @GetMapping("/{id}")
//    public Mono<StudentDto> getStudentById(@PathVariable long id) {
//        return studentService.getStudentById(id);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PostMapping
//    public Mono<StudentDto> createStudent(@RequestBody CreateStudentDto student) {
//        return studentService.createStudent(student);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PutMapping("/{id}")
//    public Mono<StudentDto> updateStudent(@PathVariable long id, @RequestBody UpdateStudentDto student) {
//        return studentService.updateStudent(id, student);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @DeleteMapping("/{id}")
//    public Mono<Void> deleteStudent(@PathVariable long id) {
//        return studentService.deleteStudent(id);
//    }
//}
