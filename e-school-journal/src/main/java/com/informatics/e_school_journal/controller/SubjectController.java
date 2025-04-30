package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.data.entity.Subject;
import com.informatics.e_school_journal.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public Mono<Subject> createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public Mono<Subject> getSubjectById(@PathVariable long id){
        return subjectService.getSubjectById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public Flux<Subject> getSubjects(){
        return subjectService.getSubjects();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public Mono<Subject> updateSubject(@PathVariable long id, @RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteSubject(@PathVariable long id) {
        return subjectService.deleteSubject(id);
    }

}
