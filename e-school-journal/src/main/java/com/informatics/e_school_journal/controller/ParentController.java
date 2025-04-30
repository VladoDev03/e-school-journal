package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.data.entity.Parent;
import com.informatics.e_school_journal.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parents")
public class ParentController {
    private final ParentService parentService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public Flux<Parent> getParents() {
        return parentService.getParents();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public Mono<Parent> getParentById(@PathVariable long id) {
        return parentService.getParentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public Mono<Parent> createParent(@RequestBody Parent parent) {
        return parentService.createParent(parent);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public Mono<Parent> updateParent(@RequestBody Parent parent) {
        return parentService.updateParent(parent);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteParent(@PathVariable long id) {
        return parentService.deleteParent(id);
    }
}
