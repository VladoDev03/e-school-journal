package com.informatics.e_school_journal.data.entity;

import com.informatics.e_school_journal.data.enums.Term;
import com.informatics.e_school_journal.data.enums.WeekDay;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.aspectj.lang.annotation.After;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Schedule extends BaseEntity {
    @Column(nullable = false)
    @Positive
    private int year;

    @Column(nullable = false)
    private Term term;

    @Column(name = "week_day", nullable = false)
    private WeekDay weekDay;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Studying studying;
}
