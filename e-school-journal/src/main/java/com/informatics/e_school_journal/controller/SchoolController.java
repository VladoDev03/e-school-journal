package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.school.CreateSchoolDto;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.dto.school.UpdateSchoolDto;
import com.informatics.e_school_journal.service.SchoolService;
import com.informatics.e_school_journal.service.impl.SchoolServiceImpl;
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
@RequestMapping("/api/school")

public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<SchoolDto> getSchools() {
        return this.schoolService.getAllSchools();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('director') or hasAuthority('teacher') or hasAuthority('student') or hasAuthority('parent')")
    @GetMapping("/{id}")
    public SchoolDto getSchoolById(@PathVariable String id) {
        return this.schoolService.getSchoolById(id);
    }

    @PostMapping
    public SchoolDto createSchool(@RequestBody @Valid CreateSchoolDto createSchoolDto) {
        return this.schoolService.createSchool(createSchoolDto);
    }

    @PutMapping("/{id}")
    public SchoolDto updateSchool(@PathVariable String id, @RequestBody @Valid UpdateSchoolDto updateSchoolDto) {
        return this.schoolService.updateSchool(id, updateSchoolDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable String id) {
        this.schoolService.deleteSchool(id);
    }
}

