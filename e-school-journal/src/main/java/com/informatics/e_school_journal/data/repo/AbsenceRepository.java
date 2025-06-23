package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
}
