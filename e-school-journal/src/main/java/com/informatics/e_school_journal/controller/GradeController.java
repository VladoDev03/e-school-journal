package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableReactiveMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grade")
public class GradeController {
    private final GradeService gradeService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public Mono<Grade> createGrade(@RequestBody Grade grade) {
        return gradeService.createGrade(grade);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public Mono<Grade> getGradeById(@PathVariable long id) {
        return gradeService.getGradeById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public Flux<Grade> getGrades() {
        return gradeService.getGrades();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public Mono<Grade> updateGrade(@PathVariable long id, @RequestBody Grade grade) {
        return gradeService.updateGrade(id, grade);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteGrade(@PathVariable long id) {
        return gradeService.deleteGrade(id);
    }
}
