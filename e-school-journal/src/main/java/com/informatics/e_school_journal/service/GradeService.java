package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.grade.CreateGradeDto;
import com.informatics.e_school_journal.dto.grade.GradeDto;
import com.informatics.e_school_journal.dto.grade.UpdateGradeDto;

import java.util.List;

public interface GradeService {
    GradeDto getGradeById(String id);
    GradeDto createGrade(CreateGradeDto createGradeDto);
    List<GradeDto> getGrades();
    GradeDto updateGrade(String id, UpdateGradeDto updateGradeDto);
    void deleteGrade(String id);
    List<GradeDto> getGradesInSchool(String schoolId) throws Exception;
}

