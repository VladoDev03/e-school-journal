package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
