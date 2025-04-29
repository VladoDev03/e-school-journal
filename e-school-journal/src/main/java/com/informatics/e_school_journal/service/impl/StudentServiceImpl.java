package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Student;
import com.informatics.e_school_journal.data.repo.StudentRepository;
import com.informatics.e_school_journal.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Flux<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> getStudentById(long id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public Mono<Student> createStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Mono<Student> updateStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Mono<Void> deleteStudent(long id) {
        return this.studentRepository.deleteById(id);
    }
}
