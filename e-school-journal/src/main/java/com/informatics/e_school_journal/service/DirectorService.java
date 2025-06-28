package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.director.CreateDirectorDto;
import com.informatics.e_school_journal.dto.director.DirectorDto;
import com.informatics.e_school_journal.dto.director.UpdateDirectorDto;

import java.util.List;

public interface DirectorService {
    List<DirectorDto> getAllDirectors();
    DirectorDto getDirectorById(String id);
    DirectorDto createDirector(CreateDirectorDto createDirectorDto);
    DirectorDto updateDirector(String id, UpdateDirectorDto updateDirectorDto);
    void deleteDirector(String id);
    DirectorDto getDirectorBySchoolId(String schoolId);
}
