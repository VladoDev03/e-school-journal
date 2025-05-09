package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Teacher;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TeacherRepository extends R2dbcRepository<Teacher, Long> {
}

