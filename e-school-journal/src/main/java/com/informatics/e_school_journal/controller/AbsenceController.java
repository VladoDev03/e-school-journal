package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.absence.AbsenceDto;
import com.informatics.e_school_journal.dto.absence.CreateAbsenceDto;
import com.informatics.e_school_journal.dto.absence.UpdateAbsenceDto;
import com.informatics.e_school_journal.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/absence")
public class AbsenceController {
    private final AbsenceService absenceService;

    @PostMapping
    public AbsenceDto createAbsence(@RequestBody CreateAbsenceDto absenceDto) {
        return absenceService.createAbsence(absenceDto);
    }

    @PutMapping("/{id}")
    public AbsenceDto updateAbsence(@PathVariable long id, @RequestBody UpdateAbsenceDto absenceDto) {
        return absenceService.updateAbsence(id, absenceDto);
    }

    @DeleteMapping("/{id}/{teacherId}")
    public void deleteAbsence(@PathVariable long id, @PathVariable long teacherId) {
        absenceService.deleteAbsence(id, teacherId);
    }
}
