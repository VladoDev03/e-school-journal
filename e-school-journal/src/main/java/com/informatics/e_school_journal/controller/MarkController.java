package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.mark.*;
import com.informatics.e_school_journal.dto.school.SchoolAvgMarkDto;
import com.informatics.e_school_journal.dto.subject.SubjectAvgMarkDto;
import com.informatics.e_school_journal.dto.teacher.TeacherAvgMarkDto;
import com.informatics.e_school_journal.service.MarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mark")
public class MarkController {
    private final MarkService markService;

    // Get -> all auth

    @PostMapping
    public MarkDto createMark(@RequestBody @Valid CreateMarkDto createMarkDto) {
        return this.markService.createMark(createMarkDto);
    }

    @PutMapping("/{id}")
    public MarkDto updateMark(@PathVariable String id, @RequestBody @Valid UpdateMarkDto updateMarkDto) {
        return this.markService.updateMark(id, updateMarkDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMark(@PathVariable String id) {
        this.markService.deleteMark(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('student') or hasAuthority('parent') or hasAuthority('director')")
    @GetMapping("/{id}")
    public MarkWithSubjectDto getMark(@PathVariable String id) {
        return this.markService.getMark(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('student') or hasAuthority('parent') or hasAuthority('director')")
    @GetMapping("/student/{studentId}")
    public List<MarkWithSubjectDto> getMarksByStudent(@PathVariable String studentId) {
        return this.markService.getMarksByStudent(studentId);
    }

    @PreAuthorize("hasAuthority('director')")
    @GetMapping("/director/stats-by-teacher")
    public List<TeacherAvgMarkDto> getMarksStatsByTeacherByDirector() {
        return this.markService.getAvgMarksByTeacherByDirector();
    }

    @PreAuthorize("hasAuthority('director')")
    @GetMapping("/director/stats-by-subject")
    public List<SubjectAvgMarkDto> getMarksStatsBySubjectByDirector() {
        return this.markService.getAvgMarksBySubjectByDirector();
    }

    @PreAuthorize("hasAuthority('director')")
    @GetMapping("/director/stats-by-school")
    public SchoolAvgMarkDto getMarksStatsByDirector() {
        return this.markService.getAvgMarkBySchoolByDirector();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/stats-by-subject")
    public List<SubjectAvgMarkDto> getMarksStatsBySubject() {
        return this.markService.getAvgMarksBySubject();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/stats-by-school")
    public List<SchoolAvgMarkDto> getMarksStatsBySchool() {
        return this.markService.getAvgMarkBySchool();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/stats-by-school-and-subject")
    public List<SchoolSubjectAvgMarkDto> getMarksStatsBySchoolAndSubject() {
        return this.markService.getAvgMarkBySchoolAndSubject();
    }

}
