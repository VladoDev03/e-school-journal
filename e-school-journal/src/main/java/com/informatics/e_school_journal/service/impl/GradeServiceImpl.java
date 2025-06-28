package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.data.entity.School;
import com.informatics.e_school_journal.data.repo.GradeRepository;
import com.informatics.e_school_journal.data.repo.SchoolRepository;
import com.informatics.e_school_journal.dto.grade.CreateGradeDto;
import com.informatics.e_school_journal.dto.grade.GradeDto;
import com.informatics.e_school_journal.dto.grade.UpdateGradeDto;
import com.informatics.e_school_journal.dto.school.SchoolDto;
import com.informatics.e_school_journal.service.GradeService;
import com.informatics.e_school_journal.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final SchoolRepository schoolRepository;
    private final ModelMapperConfig mapperConfig;

    private final SchoolService schoolService;

    @Override
    public GradeDto createGrade(CreateGradeDto createGradeDto) {
        Grade grade = new Grade();
        grade.setGrade(createGradeDto.getGrade());
        grade.setStream(createGradeDto.getStream());
        grade.setYear(createGradeDto.getYear());

        School school = schoolRepository.findById(createGradeDto.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found with id: " + createGradeDto.getSchoolId()));
        grade.setSchool(school);

        Grade savedGrade = gradeRepository.save(grade);
        return mapperConfig.getModelMapper().map(savedGrade, GradeDto.class);
    }

    @Override
    public GradeDto getGradeById(String id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));

        return mapperConfig.getModelMapper().map(grade, GradeDto.class);
    }

    @Override
    public List<GradeDto> getGrades() {
        return this.gradeRepository.findAll()
                .stream()
                .map(grade -> this.mapperConfig
                        .getModelMapper()
                        .map(grade, GradeDto.class))
                .toList();
    }

    @Override
    public GradeDto updateGrade(String id, UpdateGradeDto updateGradeDto) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
        existingGrade.setGrade(updateGradeDto.getGrade());
        existingGrade.setStream(updateGradeDto.getStream());

        if (updateGradeDto.getSchoolId() != null) {
            School school = schoolRepository.findById(updateGradeDto.getSchoolId())
                    .orElseThrow(() -> new RuntimeException("School not found with id: " + updateGradeDto.getSchoolId()));
            existingGrade.setSchool(school);
        }

        Grade updatedGrade = gradeRepository.save(existingGrade);
        return mapperConfig.getModelMapper().map(updatedGrade, GradeDto.class);
    }

    @Override
    public void deleteGrade(String id) {
        if (!gradeRepository.existsById(id)) {
            throw new RuntimeException("Grade not found with id: " + id);
        }
        gradeRepository.deleteById(id);
    }

    @Override
    public List<GradeDto> getGradesInSchool(String schoolId) throws Exception {
        SchoolDto school = schoolService.getSchoolById(schoolId);

        return this.gradeRepository.findGradesBySchoolId(schoolId).stream()
                .map(grade -> this.mapperConfig
                        .getModelMapper()
                        .map(grade, GradeDto.class))
                .toList();
    }
}
