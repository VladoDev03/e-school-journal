package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UpdateUserDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/possible-roles/{userId}")
    public List<RoleDto> getUnassignedUserRoles(@PathVariable String userId) {
        return this.userService.getUserPossibleRoles(userId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return this.userService.getUserByEmail(email);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody @Valid UpdateUserDto updateUserDto) {
        this.userService.updateUser(userId, updateUserDto);
    }
}
