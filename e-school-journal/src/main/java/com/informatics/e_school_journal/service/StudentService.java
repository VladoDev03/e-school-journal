//package com.informatics.e_school_journal.service;
//
//import com.informatics.e_school_journal.dto.student.CreateStudentDto;
//import com.informatics.e_school_journal.dto.student.StudentDto;
//import com.informatics.e_school_journal.dto.student.UpdateStudentDto;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//public interface StudentService {
//    Flux<StudentDto> getStudents();
//    Mono<StudentDto> getStudentById(long id);
//    Mono<StudentDto> createStudent(CreateStudentDto createStudentDto);
//    Mono<StudentDto> updateStudent(long id, UpdateStudentDto updateStudentDto);
//    Mono<Void> deleteStudent(long id);
//}
