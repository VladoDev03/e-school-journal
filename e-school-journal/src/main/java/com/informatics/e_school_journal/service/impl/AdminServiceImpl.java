package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Admin;
import com.informatics.e_school_journal.data.repo.AdminRepository;
import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;
import com.informatics.e_school_journal.dto.user.RoleDto;
import com.informatics.e_school_journal.dto.user.UserDto;
import com.informatics.e_school_journal.exception.EntityNotFoundException;
import com.informatics.e_school_journal.service.AdminService;
import com.informatics.e_school_journal.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapperConfig mapperConfig;

    private final UserService userService;

    @Override
    public List<AdminDto> getAdmins() {
        return this.adminRepository.findAll()
                .stream()
                .map(admin -> this.mapperConfig
                        .getModelMapper()
                        .map(admin, AdminDto.class))
                .toList();
    }

    @Override
    public AdminDto getAdminById(String id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));

        return mapperConfig.getModelMapper().map(admin, AdminDto.class);
    }

    @Override
    @Transactional
    public AdminDto createAdmin(CreateAdminDto createAdminDto) {
        userService.registerUser(createAdminDto.getCreateUserDto());
        UserDto userDto = userService.getUserByEmail(createAdminDto.getCreateUserDto().getEmail());

        RoleDto roleDto = userService.getRoleByName("admin");
        userService.setRole(userDto.getId(), roleDto);

        Admin admin = new Admin(userDto.getId());
        Admin savedAdmin = adminRepository.save(admin);

        return mapperConfig.getModelMapper().map(savedAdmin, AdminDto.class);
    }

    @Override
    @Transactional
    public AdminDto createAdminRole(String userId) {
        if (userService.getUserPossibleRoles(userId).stream().noneMatch(role -> role.getName().equals("admin"))) {
            throw new IllegalArgumentException("User cannot be assigned this role.");
        }

        RoleDto roleDto = userService.getRoleByName("admin");
        userService.setRole(userId, roleDto);

        Admin admin = new Admin(userId);
        Admin savedAdmin = adminRepository.save(admin);

        return mapperConfig.getModelMapper().map(savedAdmin, AdminDto.class);
    }

    @Override
    public AdminDto updateAdmin(String id, UpdateAdminDto updateAdminDto) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
        mapperConfig.getModelMapper().map(updateAdminDto, existingAdmin);
        Admin updatedAdmin = adminRepository.save(existingAdmin);

        return mapperConfig.getModelMapper().map(updatedAdmin, AdminDto.class);
    }

    @Override
    public void deleteAdmin(String id) {
        if (!adminRepository.existsById(id)) {
            throw new EntityNotFoundException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }
}
