//package com.informatics.e_school_journal.controller;
//
//import com.informatics.e_school_journal.dto.DirectorDtos.CreateDirectorDto;
//import com.informatics.e_school_journal.dto.DirectorDtos.DirectorDto;
//import com.informatics.e_school_journal.dto.DirectorDtos.UpdateDirectorDto;
//import com.informatics.e_school_journal.dto.schoolDtos.CreateSchoolDto;
//import com.informatics.e_school_journal.dto.schoolDtos.SchoolDto;
//import com.informatics.e_school_journal.dto.schoolDtos.UpdateSchoolDto;
//import com.informatics.e_school_journal.service.impl.DirectorServiceImpl;
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
//@RequestMapping("/api/director")
//@RequiredArgsConstructor
//public class DirectorController {
//    private final DirectorServiceImpl directorService;
//
//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping
//    public Flux<DirectorDto> getDirectors() {
//        return this.directorService.getAllDirectors();
//    }
//
//    @PreAuthorize("hasAnyAuthority('admin') or hasAnyAuthority('director') or hasAnyAuthority('teacher') or hasAnyAuthority('student') or hasAnyAuthority('parent')")
//    @GetMapping("/{id}")
//    public Mono<DirectorDto> getDirectorById(@PathVariable long id) {
//        return this.directorService.getDirectorById(id);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PostMapping
//    public Mono<DirectorDto> createDirector(@RequestBody CreateDirectorDto createDirectorDto) {
//        return this.directorService.createDirector(createDirectorDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PutMapping("/{id}")
//    public Mono<DirectorDto> updateDirector(@PathVariable long id, @RequestBody UpdateDirectorDto updateDirectorDto) {
//        return this.directorService.updateDirector(id, updateDirectorDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @DeleteMapping("/{id}")
//    public Mono<Void> deleteDirector(@PathVariable long id) {
//        return this.directorService.deleteDirector(id);
//    }
//
//
//}
//
