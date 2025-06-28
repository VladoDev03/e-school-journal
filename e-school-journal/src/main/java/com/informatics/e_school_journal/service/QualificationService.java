package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.qualification.CreateQualificationDto;
import com.informatics.e_school_journal.dto.subject.SubjectDto;

import java.util.List;

public interface QualificationService {
    CreateQualificationDto createQualification(CreateQualificationDto createQualificationDto);
    List<SubjectDto> getAllTeacherQualifications(String teacherId);
    void deleteQualification(String teacherId, String subjectId);
    void deleteAllTeacherQualifications(String teacherId);
}
