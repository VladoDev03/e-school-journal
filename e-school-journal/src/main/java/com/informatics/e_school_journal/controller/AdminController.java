package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.AdminDtos.CreateAdminDto;
import com.informatics.e_school_journal.dto.AdminDtos.AdminDto;
import com.informatics.e_school_journal.dto.AdminDtos.UpdateAdminDto;
import com.informatics.e_school_journal.service.impl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceImpl adminService;

//    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping
    public Flux<AdminDto> getAdmins() {
        return this.adminService.getAllAdmins();
    }

//    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/{id}")
    public Mono<AdminDto> getAdminById(@PathVariable long id) {
        return this.adminService.getAdminById(id);
    }

//    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping
    public Mono<AdminDto> createAdmin(@RequestBody CreateAdminDto createAdminDto) {
        return this.adminService.createAdmin(createAdminDto);
    }

//    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping("/{id}")
    public Mono<AdminDto> updateAdmin(@PathVariable long id, @RequestBody UpdateAdminDto updateAdminDto) {
        return this.adminService.updateAdmin(id, updateAdminDto);
    }

//    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteAdmin(@PathVariable long id) {
        return this.adminService.deleteAdmin(id);
    }


}

