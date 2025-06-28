package com.informatics.e_school_journal.data.repo;

import com.informatics.e_school_journal.data.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}
