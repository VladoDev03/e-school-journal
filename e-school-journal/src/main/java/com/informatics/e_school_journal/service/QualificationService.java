package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.qualification.CreateQualificationDto;
import com.informatics.e_school_journal.dto.subject.SubjectDto;

import java.util.List;

public interface QualificationService {
    CreateQualificationDto createQualification(CreateQualificationDto createQualificationDto);
    List<SubjectDto> getAllTeacherQualifications(Long teacherId);
    void deleteQualification(Long teacherId, Long subjectId);
    void deleteAllTeacherQualifications(Long teacherId);
}
