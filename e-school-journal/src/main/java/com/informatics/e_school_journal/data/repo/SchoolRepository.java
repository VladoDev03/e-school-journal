package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
