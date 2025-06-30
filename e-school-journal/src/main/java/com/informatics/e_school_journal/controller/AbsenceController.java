package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.absence.*;
import com.informatics.e_school_journal.dto.mark.MarkWithSubjectDto;
import com.informatics.e_school_journal.dto.mark.SchoolSubjectAvgMarkDto;
import com.informatics.e_school_journal.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/absence")
public class AbsenceController {
    private final AbsenceService absenceService;

    // Get -> all auth

    @PostMapping
    public AbsenceDto createAbsence(@RequestBody CreateAbsenceDto absenceDto) {
        return absenceService.createAbsence(absenceDto);
    }

    @PutMapping("/{id}")
    public AbsenceDto updateAbsence(@PathVariable String id, @RequestBody UpdateAbsenceDto absenceDto) {
        return absenceService.updateAbsence(id, absenceDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAbsence(@PathVariable String id) {
        absenceService.deleteAbsence(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('student') or hasAuthority('parent') or hasAuthority('director')")
    @GetMapping("/student/{studentId}")
    public List<AbsenceWithSubjectDto> getAbsencesByStudent(@PathVariable String studentId) {
        return this.absenceService.getAbsencesByStudent(studentId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/stats-by-school-and-subject")
    public List<SchoolSubjectCountAbsenceDto> getMarksStatsBySchoolAndSubject() {
        return this.absenceService.getCountAbsenceBySchoolAndSubject();
    }
}
