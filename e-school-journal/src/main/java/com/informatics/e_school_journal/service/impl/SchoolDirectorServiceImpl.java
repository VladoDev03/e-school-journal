package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Director;
import com.informatics.e_school_journal.data.entity.School;
import com.informatics.e_school_journal.data.repo.DirectorRepository;
import com.informatics.e_school_journal.data.repo.SchoolRepository;
import com.informatics.e_school_journal.dto.director.CreateDirectorDto;
import com.informatics.e_school_journal.dto.director.DirectorWithSchoolDto;
import com.informatics.e_school_journal.dto.director.UpdateSchoolDirectorDto;
import com.informatics.e_school_journal.service.SchoolDirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolDirectorServiceImpl implements SchoolDirectorService {
    private final ModelMapperConfig mapperConfig;
    private final DirectorRepository directorRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public DirectorWithSchoolDto saveDirectorWithSchool(CreateDirectorDto director) {
        Director newDirector = mapperConfig.getModelMapper().map(director, Director.class);

        School school = schoolRepository
                .findById(director.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found with id: " + director.getSchoolId()));

        newDirector.setSchool(school);

        newDirector = directorRepository.save(newDirector);

        return mapperConfig.getModelMapper().map(newDirector, DirectorWithSchoolDto.class);
    }

    @Override
    public DirectorWithSchoolDto updateDirectorWithSchool(UpdateSchoolDirectorDto director) {
        Director existingDirector = directorRepository
                .findById(director.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + director.getDirectorId()));

        School school = schoolRepository
                .findById(director.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found with id: " + director.getSchoolId()));

        existingDirector.setSchool(school);

        existingDirector = directorRepository.save(existingDirector);

        return mapperConfig.getModelMapper().map(existingDirector, DirectorWithSchoolDto.class);
    }

    @Override
    public void deleteDirectorWithSchool(String directorId) {
        Director director = directorRepository.findById(directorId).orElseThrow();
        School school = director.getSchool();

        school.setDirector(null);
        director.setSchool(null);

        directorRepository.deleteById(directorId);
    }
}
