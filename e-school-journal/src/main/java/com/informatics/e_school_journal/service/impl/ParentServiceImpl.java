package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Parent;
import com.informatics.e_school_journal.data.repo.ParentRepository;
import com.informatics.e_school_journal.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;

    @Override
    public Flux<Parent> getParents() {
        return this.parentRepository.findAll();
    }

    @Override
    public Mono<Parent> getParentById(long id) {
        return this.parentRepository.findById(id);
    }

    @Override
    public Mono<Parent> createParent(Parent parent) {
        return this.parentRepository.save(parent);
    }

    @Override
    public Mono<Parent> updateParent(Parent parent) {
        return this.parentRepository.save(parent);
    }

    @Override
    public Mono<Void> deleteParent(long id) {
        return this.parentRepository.deleteById(id);
    }
}
