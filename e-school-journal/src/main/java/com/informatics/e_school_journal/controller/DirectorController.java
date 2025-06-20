package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.DirectorDto.CreateDirectorDto;
import com.informatics.e_school_journal.dto.DirectorDto.DirectorDto;
import com.informatics.e_school_journal.dto.DirectorDto.UpdateDirectorDto;
import com.informatics.e_school_journal.service.impl.DirectorServiceImpl;
import lombok.RequiredArgsConstructor;
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
    private final DirectorServiceImpl directorService;

    @GetMapping
    public List<DirectorDto> getDirectors() {
        return this.directorService.getAllDirectors();
    }

    @PreAuthorize("hasAuthority('admin') or hasAnyAuthority('director') or hasAnyAuthority('teacher') or hasAnyAuthority('student') or hasAnyAuthority('parent')")
    @GetMapping("/{id}")
    public DirectorDto getDirectorById(@PathVariable long id) {
        return this.directorService.getDirectorById(id);
    }

    @PostMapping
    public DirectorDto createDirector(@RequestBody CreateDirectorDto createDirectorDto) {
        return this.directorService.createDirector(createDirectorDto);
    }

    @PutMapping("/{id}")
    public DirectorDto updateDirector(@PathVariable long id, @RequestBody UpdateDirectorDto updateDirectorDto) {
        return this.directorService.updateDirector(id, updateDirectorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable long id) {
        this.directorService.deleteDirector(id);
    }

}

