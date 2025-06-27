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
@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mark")
public class MarkController {
    private final MarkService markService;

    @PostMapping
    public MarkDto createMark(@RequestBody CreateMarkDto createMarkDto) {
        return this.markService.createMark(createMarkDto);
    }

    @PutMapping("/{id}")
    public MarkDto updateMark(@PathVariable Long id, @RequestBody UpdateMarkDto updateMarkDto) {
        return this.markService.updateMark(id, updateMarkDto);
    }

    @DeleteMapping("/{id}/teacher-id/{teacherId}")
    public void deleteMark(@PathVariable Long id, @PathVariable Long teacherId) {
        this.markService.deleteMark(id, teacherId);
    }

    @GetMapping("/{id}")
    public MarkWithSubjectDto getMark(@PathVariable Long id) {
        return this.markService.getMark(id);
    }
}
