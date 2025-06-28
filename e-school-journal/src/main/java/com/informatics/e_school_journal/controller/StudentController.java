package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.student.*;
import com.informatics.e_school_journal.service.StudentGradeService;
import com.informatics.e_school_journal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final StudentGradeService studentGradeService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<StudentDto> getStudents() {
        return this.studentService.getStudents();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable String id) {
        return this.studentService.getStudentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public StudentDto createStudent(@RequestBody CreateStudentDto student) {
        return this.studentService.createStudent(student);
    }

//    @PreAuthorize("hasAuthority('admin')")
//    @PutMapping("/{id}")
//    public StudentDto updateStudent(@PathVariable String id, @RequestBody UpdateStudentDto student) {
//        return this.studentService.updateStudent(id, student);
//    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        this.studentService.deleteStudent(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{studentId}/grade-id/{gradeId}")
    public ResponseEntity<StudentInGradeDto> enrollStudentInGrade(@PathVariable String studentId, @PathVariable String gradeId) {
        try {
            return new ResponseEntity<>(this.studentGradeService.enrollStudentInGrade(studentId, gradeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/withdraw/{studentId}")
    public ResponseEntity<StudentInGradeDto> withdrawStudentFromGrade(@PathVariable String studentId) {
        try {
            StudentInGradeDto student = this.studentGradeService.withdrawStudentFromGrade(studentId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public StudentInGradeDto updateStudentInGrade(@PathVariable String id, @RequestBody UpdateStudentInGradeDto student) {
        return this.studentGradeService.updateStudentInGrade(id, student);
    }
}