package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
