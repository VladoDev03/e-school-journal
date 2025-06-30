package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Mark;
import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.entity.Studying;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;
import com.informatics.e_school_journal.data.repo.MarkRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.data.repo.StudyingRepository;
import com.informatics.e_school_journal.data.repo.TeacherRepository;
import com.informatics.e_school_journal.dto.mark.*;
import com.informatics.e_school_journal.dto.school.SchoolAvgMarkDto;
import com.informatics.e_school_journal.dto.subject.SubjectAvgMarkDto;
import com.informatics.e_school_journal.dto.teacher.TeacherAvgMarkDto;
import com.informatics.e_school_journal.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.metrics.data.RepositoryMetricsAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final StudyingRepository studyingRepository;
    private final TeacherRepository teacherRepository;

    private final ModelMapperConfig mapperConfig;
    private final RepositoryMetricsAutoConfiguration repositoryMetricsAutoConfiguration;

//    @Override
//    public MarkDto getMarkById(String id) {
//        Mark mark = this.markRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));
//        return mapperConfig.getModelMapper().map(mark, MarkDto.class);
//    }

    @Override
    public MarkDto createMark(CreateMarkDto createMarkDto) {
        if (isAutumnAndFinal(createMarkDto.getTerm(), createMarkDto.getMarkType())) {
            throw new IllegalArgumentException("Final mark cannot be during autumn term.");
        }

        if (existingMarkTypeAndTerm(createMarkDto.getStudentId(), createMarkDto.getStudyingId(), createMarkDto.getMarkType(), createMarkDto.getTerm())) {
            throw new IllegalArgumentException("Mark already exists.");
        }

        Studying studying = this.studyingRepository.findById(createMarkDto.getStudyingId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id: " + createMarkDto.getStudyingId()));

        if (!this.isUserAuthorized(studying)) {
            throw new RuntimeException("User not authorized for this action.");
        }

        Student student = this.studentRepository.findById(createMarkDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + createMarkDto.getStudentId()));

        if (student.getGrade() != studying.getGrade()) {
            throw new RuntimeException("Student not in this grade.");
        }

        Mark mark = new Mark(
                createMarkDto.getMark(),
                createMarkDto.getMarkType(),
                createMarkDto.getTerm(),
                studying,
                student
        );

        Mark savedMark = this.markRepository.save(mark);

        return new MarkDto(
                savedMark.getId(),
                createMarkDto.getMark(),
                createMarkDto.getMarkType(),
                createMarkDto.getTerm(),
                createMarkDto.getStudyingId(),
                createMarkDto.getStudentId());
    }

    @Override
    public MarkDto updateMark(String id, UpdateMarkDto updateMarkDto) {
        if (isAutumnAndFinal(updateMarkDto.getTerm(), updateMarkDto.getMarkType())) {
            throw new IllegalArgumentException("Final mark cannot be during autumn term.");
        }

        if (existingMarkTypeAndTerm(updateMarkDto.getStudentId(), updateMarkDto.getStudyingId(), updateMarkDto.getMarkType(), updateMarkDto.getTerm())) {
            throw new IllegalArgumentException("Mark already exists.");
        }

        Studying studying = this.studyingRepository.findById(updateMarkDto.getStudyingId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id: " + updateMarkDto.getStudyingId()));

        if (!this.isUserAuthorized(studying)) {
            throw new RuntimeException("User not authorized for this action.");
        }

        Student student = this.studentRepository.findById(updateMarkDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + updateMarkDto.getStudentId()));

        if (student.getGrade() != studying.getGrade()) {
            throw new RuntimeException("Student not in this grade.");
        }

        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));
        mark.setMark(updateMarkDto.getMark());
        mark.setMarkType(updateMarkDto.getMarkType());
        mark.setStudying(studying);
        mark.setStudent(student);


        Mark savedMark = this.markRepository.save(mark);

        return new MarkDto(
                savedMark.getId(),
                updateMarkDto.getMark(),
                updateMarkDto.getMarkType(),
                updateMarkDto.getTerm(),
                updateMarkDto.getStudyingId(),
                updateMarkDto.getStudentId());
    }

    @Override
    public void deleteMark(String id) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));

        Studying studying = mark.getStudying();

        if (!this.isUserAuthorized(studying)) {
            throw new RuntimeException("User not authorized for this action.");
        }

        Student student = mark.getStudent();

        if (student.getGrade() != studying.getGrade()) {
            throw new RuntimeException("Student not in this grade.");
        }

        this.markRepository.deleteById(id);
    }

    @Override
    public MarkWithSubjectDto getMark(String id) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));

        return new MarkWithSubjectDto(
                mark.getId(),
                mark.getMark(),
                mark.getMarkType(),
                mark.getTerm(),
                mark.getStudying().getId(),
                mark.getStudent().getId(),
                mark.getStudying().getSubject().getName()
        );
    }

    @Override
    public List<MarkWithSubjectDto> getMarksByStudent(String studentId) {
        Student student = this.studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        List<Mark> marks = this.markRepository.findMarksByStudentId(studentId);
        return marks.stream().map(mark -> new MarkWithSubjectDto(
                mark.getId(),
                mark.getMark(),
                mark.getMarkType(),
                mark.getTerm(),
                mark.getStudying().getId(),
                mark.getStudent().getId(),
                mark.getStudying().getSubject().getName()
        )).toList();
    }

    @Override
    public List<TeacherAvgMarkDto> getAvgMarksByTeacherByDirector() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Object[]> rawResults = markRepository.getAvgMarksByTeacherByDirector(authentication.getName());

        return rawResults.stream()
                .map(row -> new TeacherAvgMarkDto(
                        (String) row[0],
                        row[1] != null ? Math.round(((Number) row[1]).doubleValue() * 100.0) / 100.0 : 0.0
                ))
                .toList();
    }

    @Override
    public List<SubjectAvgMarkDto> getAvgMarksBySubjectByDirector() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Object[]> rawResults = markRepository.getAvgMarksBySubjectByDirector(authentication.getName());

        return rawResults.stream()
                .map(row -> new SubjectAvgMarkDto(
                        (String) row[0],
                        row[1] != null ? Math.round(((Number) row[1]).doubleValue() * 100.0) / 100.0 : 0.0
                ))
                .toList();
    }

    @Override
    public SchoolAvgMarkDto getAvgMarkBySchoolByDirector() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Object[]> rawResults = markRepository.getAvgMarksByDirector(authentication.getName());

        return rawResults.stream()
                .map(row -> new SchoolAvgMarkDto(
                        (String) row[0],
                        row[1] != null ? Math.round(((Number) row[1]).doubleValue() * 100.0) / 100.0 : 0.0
                ))
                .toList().getFirst();
    }

    @Override
    public List<SubjectAvgMarkDto> getAvgMarksBySubject() {
        List<Object[]> rawResults = markRepository.getAvgMarksBySubject();

        return rawResults.stream()
                .map(row -> new SubjectAvgMarkDto(
                        (String) row[0],
                        row[1] != null ? Math.round(((Number) row[1]).doubleValue() * 100.0) / 100.0 : 0.0
                ))
                .toList();
    }

    @Override
    public List<SchoolAvgMarkDto> getAvgMarkBySchool() {
        List<Object[]> rawResults = markRepository.getAvgMarksBySchool();

        return rawResults.stream()
                .map(row -> new SchoolAvgMarkDto(
                        (String) row[0],
                        row[1] != null ? Math.round(((Number) row[1]).doubleValue() * 100.0) / 100.0 : 0.0
                ))
                .toList();
    }

    @Override
    public List<SchoolSubjectAvgMarkDto> getAvgMarkBySchoolAndSubject() {
        List<Object[]> rawResults = markRepository.getAvgMarksBySchoolAndSubject();

        return rawResults.stream()
                .map(row -> new SchoolSubjectAvgMarkDto(
                        (String) row[0],
                        (String) row[1],
                        row[2] != null ? Math.round(((Number) row[2]).doubleValue() * 100.0) / 100.0 : 0.0
                ))
                .toList();
    }


    private boolean isAutumnAndFinal(Term term, MarkType markType) {
        return term == Term.AUTUMN && markType == MarkType.FINAL;
    }

    private boolean existingMarkTypeAndTerm(String studentId, String studyingId, MarkType markType, Term term) {
        return markType != MarkType.CURRENT && markRepository.existsByStudentIdAndStudyingIdAndMarkTypeAndTerm(studentId, studyingId, markType, term);
    }

    private boolean isUserAuthorized(Studying studying) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication
                .getAuthorities()
                .stream()
                .anyMatch(x -> x.getAuthority().equals("admin"));

        if(!isAdmin) {
            Teacher teacher = this.teacherRepository.findById(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + authentication.getName()));
            if (teacher != studying.getTeacher())
                return false;
        }

        return true;
    }
}
