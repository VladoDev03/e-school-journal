package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.director.*;

import java.util.List;

public interface SchoolDirectorService {
    DirectorWithSchoolDto saveDirectorWithSchool(CreateDirectorDto director);
    DirectorWithSchoolDto updateDirectorWithSchool(UpdateSchoolDirectorDto director);
    DirectorDto createDirectorRole(String userId, DirectorSchoolRoleDto director);
    List<DirectorFullInfoDto> getDirectorsWithSchools();
    void deleteDirectorWithSchool(String directorId);
}
