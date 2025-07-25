package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.grade.CreateGradeDto;
import com.informatics.e_school_journal.dto.grade.GradeDto;
import com.informatics.e_school_journal.dto.grade.UpdateGradeDto;
import com.informatics.e_school_journal.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public GradeDto createGrade(@RequestBody @Valid CreateGradeDto createGradeDto) {
        return this.gradeService.createGrade(createGradeDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public GradeDto getGradeById(@PathVariable String id) {
        return this.gradeService.getGradeById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<GradeDto> getGrades() {
        return this.gradeService.getGrades();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public GradeDto updateGrade(@PathVariable String id, @RequestBody @Valid UpdateGradeDto updateGradeDto) {
        return this.gradeService.updateGrade(id, updateGradeDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable String id) {
        this.gradeService.deleteGrade(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('director')")
    @GetMapping("/school-id/{schoolId}")
    public ResponseEntity<List<GradeDto>> getGradesInSchool(@PathVariable String schoolId) {
        try {
            return new ResponseEntity<>(this.gradeService.getGradesInSchool(schoolId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
