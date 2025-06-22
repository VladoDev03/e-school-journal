package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.studying.CreateStudyingDto;
import com.informatics.e_school_journal.dto.studying.StudyingDto;
import com.informatics.e_school_journal.dto.studying.UpdateStudyingDto;

public interface StudyingService {
    StudyingDto createStudyingDto(CreateStudyingDto createStudyingDto);
    StudyingDto updateStudying(Long id, UpdateStudyingDto updateStudyingDto);
    void deleteTeacherWithStudyings(Long teacherId);
}
