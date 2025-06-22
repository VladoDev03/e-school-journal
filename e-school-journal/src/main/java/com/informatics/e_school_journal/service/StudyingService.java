package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.studying.CreateStudyingDto;
import com.informatics.e_school_journal.dto.studying.StudyingDto;

public interface StudyingService {
    StudyingDto createStudyingDto(CreateStudyingDto createStudyingDto);
}
