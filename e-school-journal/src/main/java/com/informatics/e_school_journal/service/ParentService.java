package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.data.entity.Parent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ParentService {
    Flux<Parent> getParents();
    Mono<Parent> getParentById(long id);
    Mono<Parent> createParent(Parent parent);
    Mono<Parent> updateParent(Parent parent);
    Mono<Void> deleteParent(long id);
}
