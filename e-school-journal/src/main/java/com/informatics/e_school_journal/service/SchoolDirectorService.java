package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.director.*;

public interface SchoolDirectorService {
    DirectorWithSchoolDto saveDirectorWithSchool(CreateDirectorDto director);
    DirectorWithSchoolDto updateDirectorWithSchool(UpdateSchoolDirectorDto director);
    DirectorDto createDirectorRole(String userId, DirectorSchoolRoleDto director);
    void deleteDirectorWithSchool(String directorId);
}
