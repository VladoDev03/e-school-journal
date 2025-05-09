package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;

import java.util.List;

public interface AdminService {
    List<AdminDto> getAdmins();
    AdminDto getAdminById(long id);
    AdminDto createAdmin(CreateAdminDto createAdminDto);
    AdminDto updateAdmin(long id, UpdateAdminDto updateAdminDto);
    void deleteAdmin(long id);
}
