package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, String> {
    List<Absence> findAbsencesByStudentId(String studentId);
}
