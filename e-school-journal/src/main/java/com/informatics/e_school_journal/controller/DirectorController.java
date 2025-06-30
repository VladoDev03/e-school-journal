package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.director.*;
import com.informatics.e_school_journal.service.DirectorService;
import com.informatics.e_school_journal.service.SchoolDirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/director")
public class DirectorController {
    private final DirectorService directorService;
    private final SchoolDirectorService schoolDirectorService;

    @GetMapping
    public List<DirectorDto> getDirectors() {
        return this.directorService.getAllDirectors();
    }

    @PreAuthorize("hasAuthority('admin') or hasAnyAuthority('director') or hasAnyAuthority('teacher') or hasAnyAuthority('student') or hasAnyAuthority('parent')")
    @GetMapping("/{id}")
    public DirectorDto getDirectorById(@PathVariable String id) {
        return this.directorService.getDirectorById(id);
    }

    @PostMapping
    public DirectorDto createDirector(@RequestBody CreateDirectorDto createDirectorDto) {
        return this.directorService.createDirector(createDirectorDto);
    }

    @PutMapping("/{id}")
    public DirectorDto updateDirector(@PathVariable String id, @RequestBody UpdateDirectorDto updateDirectorDto) {
        return this.directorService.updateDirector(id, updateDirectorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable String id) {
        this.schoolDirectorService.deleteDirectorWithSchool(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAnyAuthority('director') or hasAnyAuthority('teacher') or hasAnyAuthority('student') or hasAnyAuthority('parent')")
    @GetMapping("/school-id/{id}")
    public DirectorDto getDirectorBySchoolId(@PathVariable String id) {
        return this.directorService.getDirectorBySchoolId(id);
    }

    @PostMapping("/school-id")
    public DirectorWithSchoolDto createDirectorWithSchool(@RequestBody CreateDirectorDto createDirectorDto) {
        return this.schoolDirectorService.saveDirectorWithSchool(createDirectorDto);
    }

    @PostMapping("/add-role/{userId}")
    public ResponseEntity<DirectorDto> createDirectorRole(@PathVariable String userId, @RequestBody DirectorSchoolRoleDto createDirectorDto) {
        try {
            return new ResponseEntity<>(schoolDirectorService.createDirectorRole(userId, createDirectorDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/school-id")
    public DirectorWithSchoolDto updateDirectorWithSchool(@RequestBody UpdateSchoolDirectorDto updateDirectorDto) {
        return this.schoolDirectorService.updateDirectorWithSchool(updateDirectorDto);
    }

    @GetMapping("/with-schools")
    public List<DirectorFullInfoDto> getWithSchools() {
        return schoolDirectorService.getDirectorsWithSchools();
    }
}
