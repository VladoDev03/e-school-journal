package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.*;
import com.informatics.e_school_journal.data.repo.AbsenceRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.data.repo.StudyingRepository;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.absence.*;
import com.informatics.e_school_journal.exception.EntityNotFoundException;
import com.informatics.e_school_journal.exception.StudentEnrollmentException;
import com.informatics.e_school_journal.exception.UserNotAuthorizedException;
import com.informatics.e_school_journal.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + createAbsenceDto.getStudentId()));

        Studying studying = studyingRepository.findById(createAbsenceDto.getStudyingId())
                .orElseThrow(() -> new EntityNotFoundException("Studying not found with id: " + createAbsenceDto.getStudyingId()));

        if (!this.isUserAuthorized(studying)) {
            throw new UserNotAuthorizedException("User not authorized for this action.");
        }

        if (student.getGrade() != studying.getGrade()) {
            throw new StudentEnrollmentException("Student is not enrolled in this grade.");
        }

        absence.setStudent(student);
        absence.setStudying(studying);

        absenceRepository.save(absence);

        return new AbsenceDto(
                absence.getId(),
                createAbsenceDto.getDate(),
                createAbsenceDto.isExcused(),
                createAbsenceDto.getStudentId(),
                createAbsenceDto.getStudyingId()
        );
    }

    @Override
    public AbsenceDto updateAbsence(String id, UpdateAbsenceDto updateAbsenceDto) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Absence not found with id: " + updateAbsenceDto.getStudentId()));

        Student student = studentRepository.findById(updateAbsenceDto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + updateAbsenceDto.getStudentId()));

        Studying studying = studyingRepository.findById(updateAbsenceDto.getStudyingId())
                .orElseThrow(() -> new EntityNotFoundException("Studying not found with id: " + updateAbsenceDto.getStudyingId()));

        if (!this.isUserAuthorized(studying)) {
            throw new UserNotAuthorizedException("User not authorized for this action.");
        }

        if (student.getGrade() != studying.getGrade()) {
            throw new StudentEnrollmentException("Student is not enrolled in this grade.");
        }

        absence.setStudent(student);
        absence.setStudying(studying);

        absenceRepository.save(absence);

        return new AbsenceDto(
                absence.getId(),
                updateAbsenceDto.getDate(),
                updateAbsenceDto.isExcused(),
                updateAbsenceDto.getStudentId(),
                updateAbsenceDto.getStudyingId()
        );
    }

    @Override
    public void deleteAbsence(String id) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Absence not found with id: " + id));

        Studying studying = studyingRepository.findById(absence.getStudying().getId())
                .orElseThrow(() -> new EntityNotFoundException("Studying not found with id: " + absence.getStudying().getId()));

        if (!this.isUserAuthorized(studying)) {
            throw new UserNotAuthorizedException("User not authorized for this action.");
        }

        absenceRepository.delete(absence);
    }

    @Override
    public List<AbsenceWithSubjectDto> getAbsencesByStudent(String studentId) {
        Student student = this.studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<Absence> absences = this.absenceRepository.findAbsencesByStudentId(studentId);
        return absences.stream().map(absence -> new AbsenceWithSubjectDto(
                absence.getId(),
                absence.getDate(),
                absence.isExcused(),
                absence.getStudent().getId(),
                absence.getStudying().getId(),
                absence.getStudying().getSubject().getName()
        )).toList();
    }

    @Override
    public List<SchoolSubjectCountAbsenceDto> getCountAbsenceBySchoolAndSubject() {
        List<Object[]> rawResults = absenceRepository.getCountAbsencesBySchoolAndSubject();

        return rawResults.stream()
                .map(row -> new SchoolSubjectCountAbsenceDto(
                        (String) row[0],
                        (String) row[1],
                        row[2] != null ? ((Number) row[2]).intValue() : 0)
                )
                .toList();
    }

    private boolean isUserAuthorized(Studying studying) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication
                .getAuthorities()
                .stream()
                .anyMatch(x -> x.getAuthority().equals("admin"));

        if(!isAdmin) {
            Teacher teacher = this.teacherRepository.findById(authentication.getName())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + authentication.getName()));
            if (teacher != studying.getTeacher())
                return false;
        }

        return true;
    }
}
