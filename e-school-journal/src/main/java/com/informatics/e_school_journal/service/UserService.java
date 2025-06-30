package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.user.CreateUserDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UpdateUserDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<Void> registerUser(CreateUserDto createUserDto);
    ResponseEntity<Void> updateUser (String userId, UpdateUserDto updateUserDto);
    UserDto getUserById(String id);
    UserDto getUserByEmail(String email);
    RoleDto getRoleByName(String roleName);
    List<RoleDto> getAllRoles();
    List<RoleDto> getUserRoles(String userId);
    List<RoleDto> getUserPossibleRoles(String userId);
    ResponseEntity<Void> setRole(String userId, RoleDto roleDto);

}
