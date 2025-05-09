package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.School;
import com.informatics.e_school_journal.data.repo.SchoolRepository;
import com.informatics.e_school_journal.dto.schoolDtos.CreateSchoolDto;
import com.informatics.e_school_journal.dto.schoolDtos.SchoolDto;
import com.informatics.e_school_journal.dto.schoolDtos.UpdateSchoolDto;
import com.informatics.e_school_journal.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final ModelMapperConfig mapperConfig;
    @Override
    public Mono<SchoolDto> createSchool(CreateSchoolDto createSchoolDto) {
        School school = mapperConfig.getModelMapper().map(createSchoolDto, School.class);
        return schoolRepository.save(school)
                .map(savedSchool -> mapperConfig.getModelMapper().map(savedSchool, SchoolDto.class));
    }

    @Override
    public Mono<SchoolDto> updateSchool(long id, UpdateSchoolDto updateSchoolDto) {
        return this.schoolRepository.findById(id)
                .flatMap(existingSchool -> {
                    mapperConfig.getModelMapper().map(updateSchoolDto, existingSchool);
                    return schoolRepository.save(existingSchool);
                })
                .switchIfEmpty(Mono.error(new Exception("School not found with id: " + id)))
                .map(updatedSchool -> mapperConfig.getModelMapper().map(updatedSchool, SchoolDto.class));

    }

    @Override
    public Mono<SchoolDto> getSchoolById(long id) {
        return this.schoolRepository.findById(id)
                .map(school ->
                    this.mapperConfig
                            .getModelMapper()
                            .map(school, SchoolDto.class)
                );
    }

    @Override
    public Flux<SchoolDto> getAllSchools() {
        return this.schoolRepository.findAll()
                .map(school -> this.mapperConfig
                        .getModelMapper()
                        .map(school, SchoolDto.class));
    }

    @Override
    public Mono<Void> deleteSchool(long id) {
        return this.schoolRepository.deleteById(id);
    }
}
