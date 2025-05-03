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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public Flux<AdminDto> getAdmins() {
        return this.adminRepository.findAll()
                .map(admin -> this.mapperConfig
                        .getModelMapper()
                        .map(admin, AdminDto.class));
    }

    @Override
    public Mono<AdminDto> getAdminById(long id) {
        return this.adminRepository.findById(id)
                .map(admin -> this.mapperConfig
                        .getModelMapper()
                        .map(admin, AdminDto.class));
    }

    @Override
    public Mono<AdminDto> createAdmin(CreateAdminDto createAdminDto) {
        Admin admin = mapperConfig.getModelMapper().map(createAdminDto, Admin.class);

        return this.adminRepository.save(admin)
                .map(savedAdmin -> this.mapperConfig.getModelMapper().map(savedAdmin, AdminDto.class));
    }

    @Override
    public Mono<AdminDto> updateAdmin(long id, UpdateAdminDto updateAdminDto) {
        return this.adminRepository.findById(id)
                .flatMap(existingAdmin -> {
                    mapperConfig.getModelMapper().map(updateAdminDto, existingAdmin);
                    return this.adminRepository.save(existingAdmin);
                })
                .switchIfEmpty(Mono.error(new Exception("Admin with id " + id + " was not found")))
                .map(updatedAdmin -> mapperConfig.getModelMapper().map(updatedAdmin, AdminDto.class));
    }

    @Override
    public Mono<Void> deleteAdmin(long id) {
        return this.adminRepository.deleteById(id);
    }
}
