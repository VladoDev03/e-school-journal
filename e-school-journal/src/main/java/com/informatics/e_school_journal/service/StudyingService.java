package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.studying.CreateStudyingDto;
import com.informatics.e_school_journal.dto.studying.StudyingDto;
import com.informatics.e_school_journal.dto.studying.UpdateStudyingDto;

import java.util.List;

public interface StudyingService {
    StudyingDto createStudyingDto(CreateStudyingDto createStudyingDto);
    StudyingDto updateStudying(String id, UpdateStudyingDto updateStudyingDto);
    void deleteTeacherWithStudyings(String teacherId);
    List<StudyingDto> getStudyingsByDirectorId();
}
