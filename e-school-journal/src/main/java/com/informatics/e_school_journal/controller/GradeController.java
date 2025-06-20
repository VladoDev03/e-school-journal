package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.grade.CreateGradeDto;
import com.informatics.e_school_journal.dto.grade.GradeDto;
import com.informatics.e_school_journal.dto.grade.UpdateGradeDto;
import com.informatics.e_school_journal.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grade")
public class GradeController {
    private final GradeService gradeService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public GradeDto createGrade(@RequestBody CreateGradeDto createGradeDto) {
        return this.gradeService.createGrade(createGradeDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public GradeDto getGradeById(@PathVariable long id) {
        return this.gradeService.getGradeById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<GradeDto> getGrades() {
        return this.gradeService.getGrades();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public GradeDto updateGrade(@PathVariable long id, @RequestBody UpdateGradeDto updateGradeDto) {
        return this.gradeService.updateGrade(id, updateGradeDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable long id) {
        this.gradeService.deleteGrade(id);
    }
}
