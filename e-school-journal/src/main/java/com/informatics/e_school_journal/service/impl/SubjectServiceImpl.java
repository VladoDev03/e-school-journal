package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Subject;
import com.informatics.e_school_journal.data.repo.SubjectRepository;
import com.informatics.e_school_journal.dto.SubjectDto.CreateSubjectDto;
import com.informatics.e_school_journal.dto.SubjectDto.SubjectDto;
import com.informatics.e_school_journal.dto.SubjectDto.UpdateSubjectDto;
import com.informatics.e_school_journal.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public Mono<SubjectDto> createSubject(CreateSubjectDto createSubjectDto){
        Subject subject = mapperConfig.getModelMapper().map(createSubjectDto, Subject.class);
        return this.subjectRepository.save(subject)
                .map(savedSubject -> mapperConfig.getModelMapper().map(savedSubject, SubjectDto.class));
    }

    @Override
    public Mono<SubjectDto> getSubjectById(long id) {
        return this.subjectRepository.findById(id)
                .map(subject -> this.mapperConfig
                            .getModelMapper()
                            .map(subject, SubjectDto.class));
    }

    @Override
    public Flux<SubjectDto> getSubjects(){
        return this.subjectRepository.findAll()
                .map(subject -> this.mapperConfig
                        .getModelMapper()
                        .map(subject, SubjectDto.class));
    }

    @Override
    public Mono<SubjectDto> updateSubject(long id, UpdateSubjectDto updateSubjectDto) {
        return this.subjectRepository.findById(id)
                .flatMap(existingSubject -> {
                    mapperConfig.getModelMapper().map(updateSubjectDto, existingSubject);
                    return this.subjectRepository.save(existingSubject);
                })
                .switchIfEmpty(Mono.error(new Exception("Subject not found with id: " + id)))
                .map(updatedSubject -> mapperConfig.getModelMapper().map(updatedSubject, SubjectDto.class));
    }

    @Override
    public Mono<Void> deleteSubject(long id) {
        return this.subjectRepository.deleteById(id);
    }

}
