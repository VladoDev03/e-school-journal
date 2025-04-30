package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Grade;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface GradeRepository extends R2dbcRepository<Grade, Long> {

}
