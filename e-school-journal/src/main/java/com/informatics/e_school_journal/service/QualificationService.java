package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.qualification.CreateQualificationDto;

public interface QualificationService {
    CreateQualificationDto createQualification(CreateQualificationDto createQualificationDto);
    void deleteQualification(Long teacherId, Long subjectId);
    void deleteAllTeacherQualifications(Long teacherId);
}
