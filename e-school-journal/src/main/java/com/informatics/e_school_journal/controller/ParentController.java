//package com.informatics.e_school_journal.controller;
//
//import com.informatics.e_school_journal.dto.parent.CreateParentDto;
//import com.informatics.e_school_journal.dto.parent.ParentDto;
//import com.informatics.e_school_journal.dto.parent.UpdateParentDto;
//import com.informatics.e_school_journal.service.ParentService;
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
//@RequestMapping("/api/parents")
//public class ParentController {
//    private final ParentService parentService;
//
//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping
//    public Flux<ParentDto> getParents() {
//        return parentService.getParents();
//    }
//
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
//    @GetMapping("/{id}")
//    public Mono<ParentDto> getParentById(@PathVariable long id) {
//        return parentService.getParentById(id);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PostMapping
//    public Mono<ParentDto> createParent(@RequestBody CreateParentDto createParentDto) {
//        return parentService.createParent(createParentDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @PutMapping("/{id}")
//    public Mono<ParentDto> updateParent(@PathVariable long id, @RequestBody UpdateParentDto updateParentDto) {
//        return parentService.updateParent(id, updateParentDto);
//    }
//
//    @PreAuthorize("hasAuthority('admin')")
//    @DeleteMapping("/{id}")
//    public Mono<Void> deleteParent(@PathVariable long id) {
//        return parentService.deleteParent(id);
//    }
//}
