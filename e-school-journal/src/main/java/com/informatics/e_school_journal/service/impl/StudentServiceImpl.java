//package com.informatics.e_school_journal.service.impl;
//
//import com.informatics.e_school_journal.config.ModelMapperConfig;
//import com.informatics.e_school_journal.data.entity.Student;
//import com.informatics.e_school_journal.data.repo.StudentRepository;
//import com.informatics.e_school_journal.dto.student.CreateStudentDto;
//import com.informatics.e_school_journal.dto.student.StudentDto;
//import com.informatics.e_school_journal.dto.student.UpdateStudentDto;
//import com.informatics.e_school_journal.service.StudentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//@RequiredArgsConstructor
//public class StudentServiceImpl implements StudentService {
//    private final StudentRepository studentRepository;
//    private final ModelMapperConfig mapperConfig;
//
//    @Override
//    public Flux<StudentDto> getStudents() {
//        return this.studentRepository.findAll()
//                .map(student -> this.mapperConfig
//                        .getModelMapper()
//                        .map(student, StudentDto.class));
//    }
//
//    @Override
//    public Mono<StudentDto> getStudentById(long id) {
//        return this.studentRepository.findById(id)
//                .map(student -> this.mapperConfig
//                        .getModelMapper()
//                        .map(student, StudentDto.class));
//    }
//
//    @Override
//    public Mono<StudentDto> createStudent(CreateStudentDto createStudentDto) {
//        Student student = new Student();
//
//        return this.studentRepository.save(student)
//                .map(savedStudent -> this.mapperConfig.getModelMapper().map(savedStudent, StudentDto.class));
//    }
//
//    @Override
//    public Mono<StudentDto> updateStudent(long id, UpdateStudentDto updateStudentDto) {
//        return this.studentRepository.findById(id)
//                .flatMap(existingStudent -> {
//                    mapperConfig.getModelMapper().map(updateStudentDto, existingStudent);
//                    return this.studentRepository.save(existingStudent);
//                })
//                .switchIfEmpty(Mono.error(new Exception("Student with id " + id + " was not found")))
//                .map(updatedStudent -> this.mapperConfig.getModelMapper().map(updatedStudent, StudentDto.class));
//    }
//
//    @Override
//    public Mono<Void> deleteStudent(long id) {
//        return this.studentRepository.deleteById(id);
//    }
//}
