package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Grade;
import com.informatics.e_school_journal.data.entity.Subject;
import com.informatics.e_school_journal.data.repo.GradeRepository;
import com.informatics.e_school_journal.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Override
    public Mono<Grade> createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Mono<Grade> getGradeById(long id) {
        return gradeRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Grade not found!")));
    }

    @Override
    public Flux<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Mono<Grade> updateGrade(long id, Grade grade) {
        return gradeRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Grade not found!")))
                .flatMap(existingGrade -> {
                    existingGrade.setGrade(grade.getGrade());
                    existingGrade.setStream(grade.getStream());

                    return gradeRepository.save(existingGrade);
                });
    }

    @Override
    public Mono<Void> deleteGrade(long id) {
        return gradeRepository.deleteById(id);
    }
}
