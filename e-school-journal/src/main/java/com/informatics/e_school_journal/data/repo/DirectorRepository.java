package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
