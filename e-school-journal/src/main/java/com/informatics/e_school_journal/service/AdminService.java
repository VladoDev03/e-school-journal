package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.AdminDtos.CreateAdminDto;
import com.informatics.e_school_journal.dto.AdminDtos.AdminDto;
import com.informatics.e_school_journal.dto.AdminDtos.UpdateAdminDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AdminService {
    Mono<AdminDto> createAdmin(CreateAdminDto createAdminDto);

    Mono<AdminDto> updateAdmin(long id, UpdateAdminDto updateAdminDto);

    Mono<AdminDto> getAdminById(long id);

    Flux<AdminDto> getAllAdmins();
    Mono<Void> deleteAdmin(long id);
}
