package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Parent;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ParentRepository extends R2dbcRepository<Parent, Long> {
}
