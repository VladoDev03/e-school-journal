package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Studying;
import com.informatics.e_school_journal.data.entity.Teacher;
import com.informatics.e_school_journal.data.repo.StudyingRepository;
import com.informatics.e_school_journal.data.repo.TeacherRepository;

import com.informatics.e_school_journal.service.TeacherStudyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherStudyingServiceImpl implements TeacherStudyingService {
    private final TeacherRepository teacherRepository;
    private final StudyingRepository studyingRepository;


    @Override
    public void deleteTeacherWithStudyings(Long teacherId) {
        Teacher teacher = teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        List<Studying> studyings = studyingRepository.findStudyingByTeacherId(teacherId);
        studyings.forEach(studying -> studying.setTeacher(null));
        teacher.setStudyings(null);
        teacher.setSubjects(null);

        teacherRepository.save(teacher);
        teacherRepository.deleteById(teacherId);
    }
}
