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

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public SubjectDto createSubject(CreateSubjectDto createSubjectDto) {
        Subject subject = mapperConfig.getModelMapper().map(createSubjectDto, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);

        return mapperConfig.getModelMapper().map(savedSubject, SubjectDto.class);
    }

    @Override
    public SubjectDto getSubjectById(long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));

        return mapperConfig.getModelMapper().map(subject, SubjectDto.class);
    }

    @Override
    public List<SubjectDto> getSubjects() {
        return this.subjectRepository.findAll()
                .stream()
                .map(subject -> this.mapperConfig
                        .getModelMapper()
                        .map(subject, SubjectDto.class))
                .toList();
    }

    @Override
    public SubjectDto updateSubject(long id, UpdateSubjectDto updateSubjectDto) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        mapperConfig.getModelMapper().map(updateSubjectDto, existingSubject);
        Subject updatedSubject = subjectRepository.save(existingSubject);

        return mapperConfig.getModelMapper().map(updatedSubject, SubjectDto.class);
    }

    @Override
    public void deleteSubject(long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

}