package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Admin;
import com.informatics.e_school_journal.data.repo.AdminRepository;
import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;
import com.informatics.e_school_journal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapperConfig mapperConfig;

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
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));

        return mapperConfig.getModelMapper().map(admin, AdminDto.class);
    }

    @Override
    public AdminDto createAdmin(CreateAdminDto createAdminDto) {
        Admin admin = mapperConfig.getModelMapper().map(createAdminDto, Admin.class);
        Admin savedAdmin = adminRepository.save(admin);

        return mapperConfig.getModelMapper().map(savedAdmin, AdminDto.class);
    }

    @Override
    public AdminDto updateAdmin(String id, UpdateAdminDto updateAdminDto) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
        mapperConfig.getModelMapper().map(updateAdminDto, existingAdmin);
        Admin updatedAdmin = adminRepository.save(existingAdmin);

        return mapperConfig.getModelMapper().map(updatedAdmin, AdminDto.class);
    }

    @Override
    public void deleteAdmin(String id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }
}
