package com.informatics.e_school_journal.service;

import com.informatics.e_school_journal.dto.schedule.CreateScheduleDto;
import com.informatics.e_school_journal.dto.schedule.ScheduleDto;

public interface ScheduleService {
    ScheduleDto createSchedule(CreateScheduleDto createScheduleDto);
}
