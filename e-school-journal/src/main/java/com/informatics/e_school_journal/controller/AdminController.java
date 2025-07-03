package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;
import com.informatics.e_school_journal.service.AdminService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public List<AdminDto> getAdmins() {
        return adminService.getAdmins();
    }

    @GetMapping("/{id}")
    public AdminDto getAdminById(@PathVariable String id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public AdminDto createAdmin(@RequestBody @Valid CreateAdminDto createAdminDto) {
        return adminService.createAdmin(createAdminDto);
    }

    @PostMapping("/add-role/{userId}")
    public ResponseEntity<AdminDto> createAdminRole(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(adminService.createAdminRole(userId), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public AdminDto updateAdmin(@PathVariable String id, @RequestBody @Valid UpdateAdminDto updateAdminDto) {
        return adminService.updateAdmin(id, updateAdminDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
    }
}
