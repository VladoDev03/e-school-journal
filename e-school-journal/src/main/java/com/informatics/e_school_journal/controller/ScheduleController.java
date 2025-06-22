package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.schedule.CreateScheduleDto;
import com.informatics.e_school_journal.dto.schedule.ScheduleDto;
import com.informatics.e_school_journal.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleDto createSchedule(@RequestBody CreateScheduleDto scheduleDto) {
        return this.scheduleService.createSchedule(scheduleDto);
    }
}
