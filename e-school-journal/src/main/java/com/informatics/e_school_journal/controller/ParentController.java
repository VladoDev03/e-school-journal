package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.parent.*;
import com.informatics.e_school_journal.service.ParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parent")
public class ParentController {
    private final ParentService parentService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ParentDto createParent(@RequestBody @Valid CreateParentDto createParentDto) {
        return this.parentService.createParent(createParentDto);
    }

    @PostMapping("/add-role/{userId}")
    public ResponseEntity<?> createParentRole(@PathVariable String userId, @RequestBody @Valid CreateParentRoleDto createParentRoleDto) {
        try {
            ParentDto result = parentService.createParentRole(userId, createParentRoleDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher') or hasAuthority('parent') or hasAuthority('student') or hasAuthority('director')")
    @GetMapping("/{id}")
    public ParentPersonalInfoDto getParentById(@PathVariable String id) {
        return this.parentService.getParentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public List<ParentPersonalInfoDto> getParents() {
        return this.parentService.getParents();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public ParentDto updateParent(@PathVariable String id, @RequestBody @Valid UpdateParentDto updateParentDto) {
        return this.parentService.updateParent(id, updateParentDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public void deleteParent(@PathVariable String id) {
        this.parentService.deleteParent(id);
    }
}

