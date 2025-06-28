package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.mark.CreateMarkDto;
import com.informatics.e_school_journal.dto.mark.MarkDto;
import com.informatics.e_school_journal.dto.mark.MarkWithSubjectDto;
import com.informatics.e_school_journal.dto.mark.UpdateMarkDto;
import com.informatics.e_school_journal.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mark")
public class MarkController {
    private final MarkService markService;

    // Get -> all auth

    @PostMapping
    public MarkDto createMark(@RequestBody CreateMarkDto createMarkDto) {
        return this.markService.createMark(createMarkDto);
    }

    @PutMapping("/{id}")
    public MarkDto updateMark(@PathVariable String id, @RequestBody UpdateMarkDto updateMarkDto) {
        return this.markService.updateMark(id, updateMarkDto);
    }

    @DeleteMapping("/{id}/teacher-id/{teacherId}")
    public void deleteMark(@PathVariable String id, @PathVariable String teacherId) {
        this.markService.deleteMark(id, teacherId);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('student') or hasAuthority('parent') or hasAuthority('director')")
    @GetMapping("/{id}")
    public MarkWithSubjectDto getMark(@PathVariable String id) {
        return this.markService.getMark(id);
    }
}
