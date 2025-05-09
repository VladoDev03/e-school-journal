//package com.informatics.e_school_journal.controller;
//
//import com.informatics.e_school_journal.data.entity.Teacher;
//import com.informatics.e_school_journal.dto.TeacherDto.CreateTeacherDto;
//import com.informatics.e_school_journal.dto.TeacherDto.TeacherDto;
//import com.informatics.e_school_journal.dto.TeacherDto.UpdateTeacherDto;
//import com.informatics.e_school_journal.service.TeacherService;
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
//@RequestMapping("/api/teacher")
//public class TeacherController {
//    private final TeacherService teacherService;
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PostMapping
//    public Mono<TeacherDto> createTeacher(@RequestBody CreateTeacherDto createTeacherDto) {
//        return this.teacherService.createTeacher(createTeacherDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
//    @GetMapping("/{id}")
//    public Mono<TeacherDto> getTeacherById(@PathVariable long id) {
//        return this.teacherService.getTeacherById(id);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping
//    public Flux<TeacherDto> getTeachers() {
//        return this.teacherService.getTeachers();
//    }
//
//    @PutMapping("/{id}")
//    public Mono<TeacherDto> updateTeacher(@PathVariable Long id, @RequestBody UpdateTeacherDto updateTeacherDto) {
//        return this.teacherService.updateTeacher(id, updateTeacherDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @DeleteMapping("/{id}")
//    public Mono<Void> deleteTeacher(@PathVariable long id) {
//        return this.teacherService.deleteTeacher(id);
//    }
//}
