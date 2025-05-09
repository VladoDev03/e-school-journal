package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}

