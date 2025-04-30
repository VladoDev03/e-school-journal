package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.data.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> getStudents();
    Mono<Student> getStudentById(long id);
    Mono<Student> createStudent(Student student);
    Mono<Student> updateStudent(long id, Student student);
    Mono<Void> deleteStudent(long id);
}
