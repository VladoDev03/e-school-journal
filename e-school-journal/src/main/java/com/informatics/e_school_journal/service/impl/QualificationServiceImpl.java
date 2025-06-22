package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Subject;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.SubjectRepository;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.qualification.CreateQualificationDto;
import com.informatics.e_school_journal.service.QualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QualificationServiceImpl implements QualificationService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public CreateQualificationDto createQualification(CreateQualificationDto createQualificationDto) {
        Teacher teacher = teacherRepository
                .findById(createQualificationDto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + createQualificationDto.getTeacherId()));

        if (createQualificationDto.getSubjectIds() != null) {
            Set<Subject> subjects = new HashSet<>(subjectRepository.findAllById(createQualificationDto.getSubjectIds()));
            teacher.setSubjects(subjects);
        }

        teacherRepository.save(teacher);

        return createQualificationDto;
    }

    @Override
    public void deleteQualification(Long teacherId, Long subjectId) {
        Teacher teacher = teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + teacherId));

        Subject subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id " + subjectId));

        Set<Subject> subjects = teacher.getSubjects();
        subjects.remove(subject);
        teacher.setSubjects(subjects);
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteAllTeacherQualifications(Long teacherId) {
        Teacher teacher = teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + teacherId));

        teacher.setSubjects(null);
        teacherRepository.save(teacher);
    }
}
