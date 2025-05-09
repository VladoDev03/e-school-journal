//package com.informatics.e_school_journal.service;
//
//import com.informatics.e_school_journal.data.entity.Grade;
//import com.informatics.e_school_journal.dto.GradeDto.CreateGradeDto;
//import com.informatics.e_school_journal.dto.GradeDto.GradeDto;
//import com.informatics.e_school_journal.dto.GradeDto.UpdateGradeDto;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//public interface GradeService {
//    Mono<GradeDto> createGrade(CreateGradeDto createGradeDto);
//    Mono<GradeDto> getGradeById(long id);
//    Flux<GradeDto> getGrades();
//    Mono<GradeDto> updateGrade(long id, UpdateGradeDto updateGradeDto);
//    Mono<Void> deleteGrade(long id);
//}
