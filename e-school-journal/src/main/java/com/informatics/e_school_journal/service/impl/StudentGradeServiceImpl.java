package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.config.ModelMapperConfig;
import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.repo.GradeRepository;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.dto.student.StudentInGradeDto;
import com.informatics.e_school_journal.dto.student.UpdateStudentInGradeDto;
import com.informatics.e_school_journal.service.StudentGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentGradeServiceImpl implements StudentGradeService {
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final ModelMapperConfig mapperConfig;
    @Override
    public StudentInGradeDto enrollStudentInGrade(Long studentId, Long gradeId){
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Grade grade = this.gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + gradeId));

        existingStudent.setGrade(grade);
        Student updatedStudent = this.studentRepository.save(existingStudent);
        return mapperConfig.getModelMapper().map(updatedStudent, StudentInGradeDto.class);
    }

    @Override
    public StudentInGradeDto withdrawStudentFromGrade(Long studentId) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        existingStudent.setGrade(null);
        Student updatedStudent = this.studentRepository.save(existingStudent);
        return mapperConfig.getModelMapper().map(updatedStudent, StudentInGradeDto.class);
    }

    @Override
    public StudentInGradeDto updateStudentInGrade(Long studentId, UpdateStudentInGradeDto updateStudentInGradeDto){
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Long gradeId = updateStudentInGradeDto.getGradeId();

        if(gradeId != null) {
            Grade grade = this.gradeRepository.findById(gradeId)
                    .orElseThrow(() -> new RuntimeException("Grade not found with id: " + gradeId));
            mapperConfig.getModelMapper().map(updateStudentInGradeDto, existingStudent);
            existingStudent.setGrade(grade);
        }

        Student updatedStudent = this.studentRepository.save(existingStudent);
        return mapperConfig.getModelMapper().map(updatedStudent, StudentInGradeDto.class);
    }
}
