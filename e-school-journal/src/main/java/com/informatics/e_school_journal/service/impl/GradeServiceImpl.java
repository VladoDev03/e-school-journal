package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.data.repo.GradeRepository;
import com.informatics.e_school_journal.dto.GradeDto.CreateGradeDto;
import com.informatics.e_school_journal.dto.GradeDto.GradeDto;
import com.informatics.e_school_journal.dto.GradeDto.UpdateGradeDto;
import com.informatics.e_school_journal.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public Mono<GradeDto> createGrade(CreateGradeDto createGradeDto) {
        Grade grade = mapperConfig.getModelMapper().map(createGradeDto, Grade.class);
        return this.gradeRepository.save(grade)
                .map(savedGrade -> mapperConfig.getModelMapper().map(savedGrade, GradeDto.class));
    }

    @Override
    public Mono<GradeDto> getGradeById(long id) {
        return this.gradeRepository.findById(id)
                .map(grade -> this.mapperConfig
                        .getModelMapper()
                        .map(grade, GradeDto.class));
    }

    @Override
    public Flux<GradeDto> getGrades() {
        return this.gradeRepository.findAll()
                .map(grade -> this.mapperConfig
                        .getModelMapper()
                        .map(grade, GradeDto.class));
    }

    @Override
    public Mono<GradeDto> updateGrade(long id, UpdateGradeDto updateGradeDto) {
        return this.gradeRepository.findById(id)
                .flatMap(existingGrade -> {
                    mapperConfig.getModelMapper().map(updateGradeDto, existingGrade);
                    return this.gradeRepository.save(existingGrade);
                })
                .switchIfEmpty(Mono.error(new Exception("Grade not found with id: " + id)))
                .map(updatedGrade -> mapperConfig.getModelMapper().map(updatedGrade, GradeDto.class));
    }

    @Override
    public Mono<Void> deleteGrade(long id) {
        return this.gradeRepository.deleteById(id);
    }
}
