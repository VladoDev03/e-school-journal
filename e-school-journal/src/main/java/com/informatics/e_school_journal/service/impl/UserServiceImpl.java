package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.RestClientConfig;
import com.informatics.e_school_journal.dto.user.CreateUserDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UpdateUserDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final RestClientConfig restClientConfig;

    @Override
    public ResponseEntity<Void> registerUser(CreateUserDto createUserDto) {
        String token = this.getAuthToken();

        return restClientConfig.getRestClient()
                .post()
                .uri("http://localhost:8080/admin/realms/e-school-journal/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .body(createUserDto)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public ResponseEntity<Void> updateUser(String userId, UpdateUserDto updateUserDto) {
        String token = this.getAuthToken();

        return restClientConfig.getRestClient()
                .put()
                .uri("http://localhost:8080/admin/realms/e-school-journal/users/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .body(updateUserDto)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        String token = this.getAuthToken();

        return restClientConfig.getRestClient()
                .get()
                .uri("http://localhost:8080/admin/realms/e-school-journal/users?email={email}&exact=true", email)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<UserDto>>() {})
                .getFirst();
    }

    @Override
    public RoleDto getRoleByName(String roleName) {
        String token = this.getAuthToken();

        return restClientConfig.getRestClient()
                .get()
                .uri("http://localhost:8080//admin/realms/e-school-journal/roles/{roleName}", roleName)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(RoleDto.class);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        String token = this.getAuthToken();

        return restClientConfig.getRestClient()
                .get()
                .uri("http://localhost:8080/admin/realms/e-school-journal/roles")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<RoleDto>>() {});
    }

    @Override
    public List<RoleDto> getUserRoles(String userId) {
        String token = this.getAuthToken();

        return restClientConfig.getRestClient()
                .get()
                .uri("http://localhost:8080/admin/realms/e-school-journal/users/{userId}/role-mappings/realm", userId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<RoleDto>>() {});
    }

    @Override
    public List<RoleDto> getUserPossibleRoles(String userId) {
        List<RoleDto> assignedRoles = getUserRoles(userId);

        if (assignedRoles.stream().anyMatch(role -> Objects.equals(role.getName(), "student"))) {
            return List.of();
        }

        List<RoleDto> allRoles = getAllRoles();
        List<RoleDto> possibleRoles = new ArrayList<>(allRoles);

        possibleRoles.removeAll(assignedRoles);

        possibleRoles = possibleRoles
                .stream()
                .filter(role ->
                        !Objects.equals(role.getName(), "uma_authorization")
                                && !Objects.equals(role.getName(), "offline_access")
                                && !Objects.equals(role.getName(), "student")
                )
                .toList();

        return possibleRoles.stream().toList();
    }

    @Override
    public ResponseEntity<Void> setRole(String userId, RoleDto roleDto) {
        String token = this.getAuthToken();

        List<RoleDto> roles = new ArrayList<>();
        roles.add(roleDto);

        return restClientConfig.getRestClient()
                .post()
                .uri("http://localhost:8080/admin/realms/e-school-journal/users/{userId}/role-mappings/realm", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .body(roles)
                .retrieve()
                .toBodilessEntity();
    }

    private String getAuthToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.getTokenValue();
    }
}
