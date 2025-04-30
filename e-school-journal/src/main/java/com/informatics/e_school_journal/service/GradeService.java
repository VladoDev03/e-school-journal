package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.data.entity.Subject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GradeService {
    Mono<Grade> createGrade(Grade grade);
    Mono<Grade> getGradeById(long id);
    Flux<Grade> getGrades();
    Mono<Grade> updateGrade(long id, Grade grade);
    Mono<Void> deleteGrade(long id);
}
