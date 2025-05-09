package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Student;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface StudentRepository extends R2dbcRepository<Student, Long> {
}
