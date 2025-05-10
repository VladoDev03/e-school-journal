package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.GradeDto.CreateGradeDto;
import com.informatics.e_school_journal.dto.GradeDto.GradeDto;
import com.informatics.e_school_journal.dto.GradeDto.UpdateGradeDto;

import java.util.List;

public interface GradeService {
    GradeDto getGradeById(long id);
    GradeDto createGrade(CreateGradeDto createGradeDto);
    List<GradeDto> getGrades();
    GradeDto updateGrade(long id, UpdateGradeDto updateGradeDto);
    void deleteGrade(long id);
}

