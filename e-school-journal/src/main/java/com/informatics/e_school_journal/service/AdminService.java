package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AdminService {
    Flux<AdminDto> getAdmins();
    Mono<AdminDto> getAdminById(long id);
    Mono<AdminDto> createAdmin(CreateAdminDto createAdminDto);
    Mono<AdminDto> updateAdmin(long id, UpdateAdminDto updateAdminDto);
    Mono<Void> deleteAdmin(long id);
}
