package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.subject.CreateSubjectDto;
import com.informatics.e_school_journal.dto.subject.SubjectDto;
import com.informatics.e_school_journal.dto.subject.UpdateSubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getSubjects();
    SubjectDto getSubjectById(long id);
    SubjectDto createSubject(CreateSubjectDto createSubjectDto);
    SubjectDto updateSubject(long id, UpdateSubjectDto updateSubjectDto);
    void deleteSubject(long id);
}