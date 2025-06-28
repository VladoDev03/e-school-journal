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
import com.informatics.e_school_journal.dto.mark.CreateMarkDto;
import com.informatics.e_school_journal.dto.mark.MarkDto;
import com.informatics.e_school_journal.dto.mark.MarkWithSubjectDto;
import com.informatics.e_school_journal.dto.mark.UpdateMarkDto;
import com.informatics.e_school_journal.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.metrics.data.RepositoryMetricsAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Teacher teacher = this.teacherRepository.findById(createMarkDto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + createMarkDto.getTeacherId()));

        Studying studying = this.studyingRepository.findById(createMarkDto.getStudyingId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id: " + createMarkDto.getStudyingId()));

        if (teacher != studying.getTeacher()) {
            throw new RuntimeException("Teacher not authorized for this action.");
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

        Teacher teacher = this.teacherRepository.findById(updateMarkDto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + updateMarkDto.getTeacherId()));

        Studying studying = this.studyingRepository.findById(updateMarkDto.getStudyingId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id: " + updateMarkDto.getStudyingId()));

        if (teacher != studying.getTeacher()) {
            throw new RuntimeException("Teacher not authorized for this action.");
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
    public void deleteMark(String id, String teacherId) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));

        Teacher teacher = this.teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        Studying studying = mark.getStudying();

        if (teacher != studying.getTeacher()) {
            throw new RuntimeException("Teacher not authorized for this action.");
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
//        if(studentRepository.findById(studentId) == null){
//
//        }
//        List<Ma>
//        Mark mark = markRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));
//
//        return new MarkWithSubjectDto(
//                mark.getId(),
//                mark.getMark(),
//                mark.getMarkType(),
//                mark.getStudying().getId(),
//                mark.getStudent().getId(),
//                mark.getStudying().getSubject().getName()
//        );
        return null;
    }

    private boolean isAutumnAndFinal(Term term, MarkType markType) {
        return term == Term.AUTUMN && markType == MarkType.FINAL;
    }

    private boolean existingMarkTypeAndTerm(String studentId, String studyingId, MarkType markType, Term term) {
        return markType != MarkType.CURRENT && markRepository.existsByStudentIdAndStudyingIdAndMarkTypeAndTerm(studentId, studyingId, markType, term);
    }
}
