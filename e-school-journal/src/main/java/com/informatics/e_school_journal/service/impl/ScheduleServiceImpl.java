package com.informatics.e_school_journal.service.impl;

import com.informatics.e_school_journal.data.entity.Schedule;
import com.informatics.e_school_journal.data.entity.Studying;
import com.informatics.e_school_journal.data.repo.ScheduleRepository;
import com.informatics.e_school_journal.data.repo.StudyingRepository;
import com.informatics.e_school_journal.dto.schedule.CreateScheduleDto;
import com.informatics.e_school_journal.dto.schedule.ScheduleDto;
import com.informatics.e_school_journal.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final StudyingRepository studyingRepository;

    private final ModelMapper modelMapper;

    @Override
    public ScheduleDto createSchedule(CreateScheduleDto createScheduleDto) {
        Schedule schedule = modelMapper.map(createScheduleDto, Schedule.class);

        Studying studying = studyingRepository
                .findById(createScheduleDto.getStudyingId())
                .orElseThrow(() -> new RuntimeException("Studying not found with id " + createScheduleDto.getStudyingId()));

        schedule.setStudying(studying);

        return modelMapper.map(scheduleRepository.save(schedule), ScheduleDto.class);
    }
}
