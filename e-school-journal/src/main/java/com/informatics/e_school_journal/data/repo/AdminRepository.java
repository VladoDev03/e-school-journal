package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Admin;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AdminRepository extends R2dbcRepository<Admin, Long> {
}
