package com.informatics.e_school_journal.dto.schedule;

import com.informatics.e_school_journal.data.enums.Term;
import com.informatics.e_school_journal.data.enums.WeekDay;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleDto {
    private String id;
    @Positive
    @NotNull
    private int year;
    @NotNull
    private Term term;
    @NotNull
    private WeekDay weekDay;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private String studyingId;
}
