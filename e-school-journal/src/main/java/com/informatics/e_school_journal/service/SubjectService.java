//package com.informatics.e_school_journal.service;
//
//import com.informatics.e_school_journal.data.entity.Subject;
//import com.informatics.e_school_journal.dto.SubjectDto.CreateSubjectDto;
//import com.informatics.e_school_journal.dto.SubjectDto.SubjectDto;
//import com.informatics.e_school_journal.dto.SubjectDto.UpdateSubjectDto;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//public interface SubjectService {
//    Mono<SubjectDto> createSubject(CreateSubjectDto createSubjectDto);
//    Mono<SubjectDto> getSubjectById(long id);
//    Flux<SubjectDto> getSubjects();
//    Mono<SubjectDto> updateSubject(long id, UpdateSubjectDto updateSubjectDto);
//    Mono<Void> deleteSubject(long id);
//}