package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}

