package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.admin.AdminDto;
import com.informatics.e_school_journal.dto.admin.CreateAdminDto;
import com.informatics.e_school_journal.dto.admin.UpdateAdminDto;

import java.util.List;

public interface AdminService {
    List<AdminDto> getAdmins();
    AdminDto getAdminById(String id);
    AdminDto createAdmin(CreateAdminDto createAdminDto);
    AdminDto updateAdmin(String id, UpdateAdminDto updateAdminDto);
    void deleteAdmin(String id);
}
