package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Subject;
import com.informatics.e_school_journal.data.repo.SubjectRepository;
import com.informatics.e_school_journal.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public Mono<Subject> createSubject(Subject subject){
        return this.subjectRepository.save(subject);
    }

    @Override
    public Mono<Subject> getSubjectById(long id) {
        return this.subjectRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Subject not found!")));
    }

    @Override
    public Flux<Subject> getSubjects(){
        return this.subjectRepository.findAll();
    }

    @Override
    public Mono<Subject> updateSubject(long id, Subject subject) {
        return subjectRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Subject not found!")))
                .flatMap(existingSubject -> {
                    existingSubject.setName(subject.getName());

                    return subjectRepository.save(existingSubject);
                });
    }

    @Override
    public Mono<Void> deleteSubject(long id) {
        return this.subjectRepository.deleteById(id);
    }

}
