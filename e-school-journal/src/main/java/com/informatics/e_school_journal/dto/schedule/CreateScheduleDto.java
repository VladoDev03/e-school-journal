package com.informatics.e_school_journal.dto.schedule;

import com.informatics.e_school_journal.data.enums.Term;
import com.informatics.e_school_journal.data.enums.WeekDay;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateScheduleDto {
    private int year;
    private Term term;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private String studyingId;
}
