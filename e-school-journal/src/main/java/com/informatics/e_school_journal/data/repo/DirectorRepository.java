package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, String> {
    Optional<Director> findBySchoolId(String schoolId);
}
