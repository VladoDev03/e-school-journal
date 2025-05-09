package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
