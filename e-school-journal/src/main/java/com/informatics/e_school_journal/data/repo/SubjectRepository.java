package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Subject;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface SubjectRepository extends R2dbcRepository<Subject, Long> {
}

