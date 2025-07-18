package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.School;
import com.informatics.e_school_journal.data.repo.SchoolRepository;
import com.informatics.e_school_journal.dto.school.CreateSchoolDto;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.dto.school.UpdateSchoolDto;
import com.informatics.e_school_journal.exception.EntityNotFoundException;
import com.informatics.e_school_journal.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public SchoolDto createSchool(CreateSchoolDto createSchoolDto) {
        School school = mapperConfig.getModelMapper().map(createSchoolDto, School.class);
        School savedSchool = schoolRepository.save(school);

        return mapperConfig.getModelMapper().map(savedSchool, SchoolDto.class);
    }

    @Override
    public SchoolDto updateSchool(String id, UpdateSchoolDto updateSchoolDto) {
        School existingSchool = schoolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
        mapperConfig.getModelMapper().map(updateSchoolDto, existingSchool);
        School updatedSchool = schoolRepository.save(existingSchool);

        return mapperConfig.getModelMapper().map(updatedSchool, SchoolDto.class);

    }

    @Override
    public SchoolDto getSchoolById(String id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));

        return mapperConfig.getModelMapper().map(school, SchoolDto.class);
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        return this.schoolRepository.findAll()
                .stream()
                .map(school -> this.mapperConfig
                        .getModelMapper()
                        .map(school, SchoolDto.class))
                .toList();
    }

    @Override
    public void deleteSchool(String id) {
        if (!schoolRepository.existsById(id)) {
            throw new EntityNotFoundException("Director not found with id: " + id);
        }
        schoolRepository.deleteById(id);
    }
}
