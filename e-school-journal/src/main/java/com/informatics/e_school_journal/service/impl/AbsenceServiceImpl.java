package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Absence;
import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.entity.Studying;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.AbsenceRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.data.repo.StudyingRepository;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.absence.AbsenceDto;
import com.informatics.e_school_journal.dto.absence.CreateAbsenceDto;
import com.informatics.e_school_journal.dto.absence.UpdateAbsenceDto;
import com.informatics.e_school_journal.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbsenceServiceImpl implements AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final StudentRepository studentRepository;
    private final StudyingRepository studyingRepository;
    private final TeacherRepository teacherRepository;

    private final ModelMapperConfig mapperConfig;

    @Override
    public AbsenceDto createAbsence(CreateAbsenceDto createAbsenceDto) {
        Absence absence = mapperConfig.getModelMapper().map(createAbsenceDto, Absence.class);

        Student student = studentRepository.findById(createAbsenceDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + createAbsenceDto.getStudentId()));

        Studying studying = studyingRepository.findById(createAbsenceDto.getStudyingId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id: " + createAbsenceDto.getStudyingId()));

        Teacher teacher = teacherRepository.findById(createAbsenceDto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + createAbsenceDto.getTeacherId()));

        if (teacher != studying.getTeacher()) {
            throw new RuntimeException("Teacher is not authorized for this action.");
        }

        if (student.getGrade() != studying.getGrade()) {
            throw new RuntimeException("Student is not enrolled in this grade.");
        }

        absence.setStudent(student);
        absence.setStudying(studying);

        absenceRepository.save(absence);

        return new AbsenceDto(
                absence.getId(),
                createAbsenceDto.getDate(),
                createAbsenceDto.isExcused(),
                createAbsenceDto.getStudentId(),
                createAbsenceDto.getStudyingId(),
                createAbsenceDto.getTeacherId()
        );
    }

    @Override
    public AbsenceDto updateAbsence(long id, UpdateAbsenceDto updateAbsenceDto) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + updateAbsenceDto.getStudentId()));

        Student student = studentRepository.findById(updateAbsenceDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + updateAbsenceDto.getStudentId()));

        Studying studying = studyingRepository.findById(updateAbsenceDto.getStudyingId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id: " + updateAbsenceDto.getStudyingId()));

        Teacher teacher = teacherRepository.findById(updateAbsenceDto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + updateAbsenceDto.getTeacherId()));

        if (teacher != studying.getTeacher()) {
            throw new RuntimeException("Teacher is not authorized for this action.");
        }

        if (student.getGrade() != studying.getGrade()) {
            throw new RuntimeException("Student is not enrolled in this grade.");
        }

        absence.setStudent(student);
        absence.setStudying(studying);

        absenceRepository.save(absence);

        return new AbsenceDto(
                absence.getId(),
                updateAbsenceDto.getDate(),
                updateAbsenceDto.isExcused(),
                updateAbsenceDto.getStudentId(),
                updateAbsenceDto.getStudyingId(),
                updateAbsenceDto.getTeacherId()
        );
    }

    @Override
    public void deleteAbsence(long id, long teacherId) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + id));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        Studying studying = studyingRepository.findById(absence.getStudying().getId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id: " + absence.getStudying().getId()));

        if (teacher != studying.getTeacher()) {
            throw new RuntimeException("Teacher is not authorized for this action.");
        }

        absenceRepository.delete(absence);
    }
}
