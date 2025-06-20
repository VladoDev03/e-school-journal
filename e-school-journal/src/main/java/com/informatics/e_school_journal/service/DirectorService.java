package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.DirectorDto.CreateDirectorDto;
import com.informatics.e_school_journal.dto.DirectorDto.DirectorDto;
import com.informatics.e_school_journal.dto.DirectorDto.UpdateDirectorDto;

import java.util.List;

public interface DirectorService {
    List<DirectorDto> getAllDirectors();
    DirectorDto getDirectorById(long id);
    DirectorDto createDirector(CreateDirectorDto createDirectorDto);
    DirectorDto updateDirector(long id, UpdateDirectorDto updateDirectorDto);
    void deleteDirector(long id);
}
