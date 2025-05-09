//package com.informatics.e_school_journal.service;
//
//import com.informatics.e_school_journal.data.entity.School;
//import com.informatics.e_school_journal.dto.schoolDtos.CreateSchoolDto;
//import com.informatics.e_school_journal.dto.schoolDtos.SchoolDto;
//import com.informatics.e_school_journal.dto.schoolDtos.UpdateSchoolDto;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//public interface SchoolService {
//    Mono<SchoolDto> createSchool(CreateSchoolDto createSchoolDto);
//
//    Mono<SchoolDto> updateSchool(long id, UpdateSchoolDto updateSchoolDto);
//
//    Mono<SchoolDto> getSchoolById(long id);
//
//    Flux<SchoolDto> getAllSchools();
//
//    Mono<Void> deleteSchool(long id);
//}
