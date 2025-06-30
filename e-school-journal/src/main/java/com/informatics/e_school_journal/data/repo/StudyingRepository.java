package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Studying;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyingRepository extends JpaRepository<Studying, String> {
    List<Studying> findStudyingByTeacherId(String teacherId);
    List<Studying> findStudyingsByGrade_School_Director_Id(String directorId);
}
