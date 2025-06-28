package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.absence.AbsenceDto;
import com.informatics.e_school_journal.dto.absence.CreateAbsenceDto;
import com.informatics.e_school_journal.dto.absence.UpdateAbsenceDto;

public interface AbsenceService {
    AbsenceDto createAbsence(CreateAbsenceDto createAbsenceDto);
    AbsenceDto updateAbsence(String id, UpdateAbsenceDto updateAbsenceDto);
    void deleteAbsence(String id, String teacherId);
}
