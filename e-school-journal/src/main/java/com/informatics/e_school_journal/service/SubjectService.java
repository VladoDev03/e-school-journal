package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.SubjectDto.CreateSubjectDto;
import com.informatics.e_school_journal.dto.SubjectDto.SubjectDto;
import com.informatics.e_school_journal.dto.SubjectDto.UpdateSubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getSubjects();
    SubjectDto getSubjectById(long id);
    SubjectDto createSubject(CreateSubjectDto createSubjectDto);
    SubjectDto updateSubject(long id, UpdateSubjectDto updateSubjectDto);
    void deleteSubject(long id);
}