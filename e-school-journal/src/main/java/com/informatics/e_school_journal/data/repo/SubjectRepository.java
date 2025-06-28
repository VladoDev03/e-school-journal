package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findSubjectsByTeachersId(String teacherId);
}

