package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.absence.*;
import com.informatics.e_school_journal.dto.mark.SchoolSubjectAvgMarkDto;

import java.util.List;

public interface AbsenceService {
    AbsenceDto createAbsence(CreateAbsenceDto createAbsenceDto);
    AbsenceDto updateAbsence(String id, UpdateAbsenceDto updateAbsenceDto);
    void deleteAbsence(String id);
    List<AbsenceWithSubjectDto> getAbsencesByStudent(String studentId);
    List<SchoolSubjectCountAbsenceDto> getCountAbsenceBySchoolAndSubject();
}
