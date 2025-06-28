package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.director.CreateDirectorDto;
import com.informatics.e_school_journal.dto.director.DirectorWithSchoolDto;
import com.informatics.e_school_journal.dto.director.UpdateSchoolDirectorDto;

public interface SchoolDirectorService {
    DirectorWithSchoolDto saveDirectorWithSchool(CreateDirectorDto director);
    DirectorWithSchoolDto updateDirectorWithSchool(UpdateSchoolDirectorDto director);
    void deleteDirectorWithSchool(String directorId);
}
