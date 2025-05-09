//package com.informatics.e_school_journal.controller;
//
//import com.informatics.e_school_journal.data.entity.Subject;
//import com.informatics.e_school_journal.dto.SubjectDto.CreateSubjectDto;
//import com.informatics.e_school_journal.dto.SubjectDto.SubjectDto;
//import com.informatics.e_school_journal.dto.SubjectDto.UpdateSubjectDto;
//import com.informatics.e_school_journal.service.SubjectService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@EnableReactiveMethodSecurity
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/subject")
//public class SubjectController {
//    private final SubjectService subjectService;
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PostMapping
//    public Mono<SubjectDto> createSubject(@RequestBody CreateSubjectDto createSubjectDto) {
//        return this.subjectService.createSubject(createSubjectDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
//    @GetMapping("/{id}")
//    public Mono<SubjectDto> getSubjectById(@PathVariable long id){
//        return this.subjectService.getSubjectById(id);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping
//    public Flux<SubjectDto> getSubjects(){
//        return this.subjectService.getSubjects();
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PutMapping("/{id}")
//    public Mono<SubjectDto> updateSubject(@PathVariable long id, @RequestBody UpdateSubjectDto updateSubjectDto) {
//        return this.subjectService.updateSubject(id, updateSubjectDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @DeleteMapping("/{id}")
//    public Mono<Void> deleteSubject(@PathVariable long id) {
//        return this.subjectService.deleteSubject(id);
//    }
//
//}
