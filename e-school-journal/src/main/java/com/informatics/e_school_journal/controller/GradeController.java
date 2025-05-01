package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.dto.GradeDto.CreateGradeDto;
import com.informatics.e_school_journal.dto.GradeDto.GradeDto;
import com.informatics.e_school_journal.dto.GradeDto.UpdateGradeDto;
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
    public Mono<GradeDto> createGrade(@RequestBody CreateGradeDto createGradeDto) {
        return this.gradeService.createGrade(createGradeDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public Mono<GradeDto> getGradeById(@PathVariable long id) {
        return this.gradeService.getGradeById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public Flux<GradeDto> getGrades() {
        return this.gradeService.getGrades();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public Mono<GradeDto> updateGrade(@PathVariable long id, @RequestBody UpdateGradeDto updateGradeDto) {
        return this.gradeService.updateGrade(id, updateGradeDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteGrade(@PathVariable long id) {
        return this.gradeService.deleteGrade(id);
    }
}
