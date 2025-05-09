package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;
import com.informatics.e_school_journal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableReactiveMethodSecurity
@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public Flux<AdminDto> getAdmins() {
        return adminService.getAdmins();
    }

    @GetMapping("/{id}")
    public Mono<AdminDto> getAdminById(@PathVariable long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public Mono<AdminDto> createAdmin(@RequestBody CreateAdminDto createAdminDto) {
        return adminService.createAdmin(createAdminDto);
    }

    @PutMapping("/{id}")
    public Mono<AdminDto> updateAdmin(@PathVariable long id, @RequestBody UpdateAdminDto updateAdminDto) {
        return adminService.updateAdmin(id, updateAdminDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAdmin(@PathVariable long id) {
        return adminService.deleteAdmin(id);
    }
}
