//package com.informatics.e_school_journal.controller;
//
//import com.informatics.e_school_journal.data.entity.School;
//import com.informatics.e_school_journal.data.repo.SchoolRepository;
//import com.informatics.e_school_journal.dto.schoolDtos.CreateSchoolDto;
//import com.informatics.e_school_journal.dto.schoolDtos.SchoolDto;
//import com.informatics.e_school_journal.dto.schoolDtos.UpdateSchoolDto;
//import com.informatics.e_school_journal.service.impl.SchoolServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@EnableReactiveMethodSecurity
//@RestController
//@RequestMapping("/api/school")
//@RequiredArgsConstructor
//
//public class SchoolController {
//    private final SchoolServiceImpl schoolService;
//
//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping
//    public Flux<SchoolDto> getSchools() {
//        return this.schoolService.getAllSchools();
//    }
//
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('director') or hasAuthority('teacher') or hasAuthority('student') or hasAuthority('parent')")
//    @GetMapping("/{id}")
//    public Mono<SchoolDto> getSchoolById(@PathVariable long id) {
//        return this.schoolService.getSchoolById(id);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PostMapping
//    public Mono<SchoolDto> createSchool(@RequestBody CreateSchoolDto createSchoolDto) {
//        System.out.println(createSchoolDto);
//        return this.schoolService.createSchool(createSchoolDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PutMapping("/{id}")
//    public Mono<SchoolDto> updateSchool(@PathVariable long id, @RequestBody UpdateSchoolDto updateSchoolDto) {
//        return this.schoolService.updateSchool(id, updateSchoolDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @DeleteMapping("/{id}")
//    public Mono<Void> deleteSchool(@PathVariable long id) {
//        return this.schoolService.deleteSchool(id);
//    }
//
//
//}
//
