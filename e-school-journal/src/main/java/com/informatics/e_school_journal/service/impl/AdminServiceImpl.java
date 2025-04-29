package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Admin;
import com.informatics.e_school_journal.data.repo.AdminRepository;
import com.informatics.e_school_journal.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Flux<Admin> getAdmins() {
        return this.adminRepository.findAll();
    }

    @Override
    public Mono<Admin> getAdminById(long id) {
        return this.adminRepository.findById(id);
    }

    @Override
    public Mono<Admin> createAdmin(Admin admin) {
        return this.adminRepository.save(admin);
    }

    @Override
    public Mono<Admin> updateAdmin(Admin admin) {
        return this.adminRepository.save(admin);
    }

    @Override
    public Mono<Void> deleteAdmin(long id) {
        return this.adminRepository.deleteById(id);
    }
}
