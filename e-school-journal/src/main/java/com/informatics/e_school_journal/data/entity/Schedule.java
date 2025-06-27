package com.informatics.e_school_journal.data.entity;

import com.informatics.e_school_journal.data.enums.Term;
import com.informatics.e_school_journal.data.enums.WeekDay;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Schedule extends BaseEntity {
    private int year;
    private Term term;

    @Column(name = "week_day")
    private WeekDay weekDay;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Studying studying;
}
