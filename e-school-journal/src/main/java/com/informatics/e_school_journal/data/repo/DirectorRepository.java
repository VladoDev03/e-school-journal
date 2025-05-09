package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Director;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends R2dbcRepository<Director, Long>  {
}
