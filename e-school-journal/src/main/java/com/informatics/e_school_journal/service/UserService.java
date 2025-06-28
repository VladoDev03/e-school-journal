package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Void> registerUser(CreateUserDto createUserDto);
    UserDto getUserByEmail(String email);
    RoleDto getRoleIdByName(String roleName);
    ResponseEntity<Void> setRole(String userId, RoleDto roleDto);
}
