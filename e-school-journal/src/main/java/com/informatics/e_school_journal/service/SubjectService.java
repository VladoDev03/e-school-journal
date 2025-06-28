package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.subject.CreateSubjectDto;
import com.informatics.e_school_journal.dto.subject.SubjectDto;
import com.informatics.e_school_journal.dto.subject.UpdateSubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getSubjects();
    SubjectDto getSubjectById(String id);
    SubjectDto createSubject(CreateSubjectDto createSubjectDto);
    SubjectDto updateSubject(String id, UpdateSubjectDto updateSubjectDto);
    void deleteSubject(String id);
}