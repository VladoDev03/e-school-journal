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

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parent")
public class ParentController {
    private final ParentService parentService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ParentDto createParent(@RequestBody CreateParentDto createParentDto) {
        return this.parentService.createParent(createParentDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public ParentDto getParentById(@PathVariable String id) {
        return this.parentService.getParentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<ParentDto> getParents() {
        return this.parentService.getParents();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public ParentDto updateParent(@PathVariable String id, @RequestBody UpdateParentDto updateParentDto) {
        return this.parentService.updateParent(id, updateParentDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteParent(@PathVariable String id) {
        this.parentService.deleteParent(id);
    }
}

