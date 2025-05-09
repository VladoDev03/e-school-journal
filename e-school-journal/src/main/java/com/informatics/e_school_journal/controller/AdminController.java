package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;
import com.informatics.e_school_journal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public List<AdminDto> getAdmins() {
        return adminService.getAdmins();
    }

    @GetMapping("/{id}")
    public AdminDto getAdminById(@PathVariable long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public AdminDto createAdmin(@RequestBody CreateAdminDto createAdminDto) {
        return adminService.createAdmin(createAdminDto);
    }

    @PutMapping("/{id}")
    public AdminDto updateAdmin(@PathVariable long id, @RequestBody UpdateAdminDto updateAdminDto) {
        return adminService.updateAdmin(id, updateAdminDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminService.deleteAdmin(id);
    }
}
