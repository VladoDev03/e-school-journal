package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.studying.CreateStudyingDto;
import com.informatics.e_school_journal.dto.studying.StudyingDto;
import com.informatics.e_school_journal.dto.studying.UpdateStudyingDto;
import com.informatics.e_school_journal.service.StudyingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/studying")
public class StudyingController {
    private final StudyingService studyingService;

    @PostMapping
    public StudyingDto createStudying(@RequestBody @Valid CreateStudyingDto createStudyingDto) {
        return studyingService.createStudyingDto(createStudyingDto);
    }

    @PutMapping("/{id}")
    public StudyingDto updateStudying(@RequestBody @Valid UpdateStudyingDto updateStudyingDto, @PathVariable String id) {
        return studyingService.updateStudying(id, updateStudyingDto);
    }

    @PreAuthorize("hasAuthority('director')")
    @GetMapping("/director")
    public List<StudyingDto> getStudyingsByDirector(){
        return this.studyingService.getStudyingsByDirectorId();
    }
}
