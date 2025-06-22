package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.data.entity.Studying;
import com.informatics.e_school_journal.data.entity.Subject;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.GradeRepository;
import com.informatics.e_school_journal.data.repo.StudyingRepository;
import com.informatics.e_school_journal.data.repo.SubjectRepository;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.studying.CreateStudyingDto;
import com.informatics.e_school_journal.dto.studying.StudyingDto;
import com.informatics.e_school_journal.service.GradeService;
import com.informatics.e_school_journal.service.StudyingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudyingServiceImpl implements StudyingService {
    private final StudyingRepository studyingRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;

    private final ModelMapperConfig mapperConfig;

    @Override
    public StudyingDto createStudyingDto(CreateStudyingDto createStudyingDto) {
        Teacher teacher = teacherRepository.findById(createStudyingDto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + createStudyingDto.getTeacherId()));

        Grade grade = gradeRepository.findById(createStudyingDto.getGradeId())
                .orElseThrow(() -> new RuntimeException("Grade not found with id " + createStudyingDto.getGradeId()));

        Subject subject = subjectRepository.findById(createStudyingDto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with id " + createStudyingDto.getSubjectId()));

        if(!subjectRepository.findSubjectsByTeachersId(teacher.getId()).contains(subject)) {
            throw new IllegalArgumentException("Teacher is not qualified for this subject.");
        }

        Studying studying = new Studying();
        studying.setTeacher(teacher);
        studying.setGrade(grade);
        studying.setSubject(subject);

        studyingRepository.save(studying);

        return mapperConfig.getModelMapper().map(studying, StudyingDto.class);
    }

}
