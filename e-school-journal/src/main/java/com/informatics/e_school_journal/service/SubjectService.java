package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.data.entity.Subject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubjectService {
    Mono<Subject> createSubject(Subject subject);
    Mono<Subject> getSubjectById(long id);
    Flux<Subject> getSubjects();
    Mono<Subject> updateSubject(long id,Subject subject);
    Mono<Void> deleteSubject(long id);
}