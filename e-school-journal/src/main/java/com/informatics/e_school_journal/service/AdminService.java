package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.data.entity.Admin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AdminService {
    Flux<Admin> getAdmins();
    Mono<Admin> getAdminById(long id);
    Mono<Admin> createAdmin(Admin admin);
    Mono<Admin> updateAdmin(Admin admin);
    Mono<Void> deleteAdmin(long id);
}
