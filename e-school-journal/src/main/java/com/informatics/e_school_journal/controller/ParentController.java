package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.parent.CreateParentDto;
import com.informatics.e_school_journal.dto.parent.ParentDto;
import com.informatics.e_school_journal.dto.parent.UpdateParentDto;
import com.informatics.e_school_journal.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parent")
public class ParentController {
    private final ParentService parentService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<ParentDto> getParents() {
        return parentService.getParents();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public ParentDto getParentById(@PathVariable long id) {
        return parentService.getParentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ParentDto createParent(@RequestBody CreateParentDto createParentDto) {
        return parentService.createParent(createParentDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public ParentDto updateParent(@PathVariable long id, @RequestBody UpdateParentDto updateParentDto) {
        return parentService.updateParent(id, updateParentDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteParent(@PathVariable long id) {
        parentService.deleteParent(id);
    }
}