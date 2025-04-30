package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public Mono<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public Mono<Teacher> getTeacherById(@PathVariable long id) {
        return teacherService.getTeacherById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public Flux<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @PutMapping("/{id}")
    public Mono<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id, teacher);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTeacher(@PathVariable long id) {
        return teacherService.deleteTeacher(id);
    }
}
