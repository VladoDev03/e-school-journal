package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.subject.CreateSubjectDto;
import com.informatics.e_school_journal.dto.subject.SubjectDto;
import com.informatics.e_school_journal.dto.subject.UpdateSubjectDto;
import com.informatics.e_school_journal.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public SubjectDto createSubject(@RequestBody CreateSubjectDto createSubjectDto) {
        return this.subjectService.createSubject(createSubjectDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public SubjectDto getSubjectById(@PathVariable String id){
        return this.subjectService.getSubjectById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<SubjectDto> getSubjects(){
        return this.subjectService.getSubjects();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public SubjectDto updateSubject(@PathVariable String id, @RequestBody UpdateSubjectDto updateSubjectDto) {
        return this.subjectService.updateSubject(id, updateSubjectDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable String id) {
        this.subjectService.deleteSubject(id);
    }

}
