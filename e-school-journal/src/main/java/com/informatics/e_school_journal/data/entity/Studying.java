package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Studying extends BaseEntity {
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToMany(mappedBy = "studying", fetch = FetchType.LAZY)
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "studying", fetch = FetchType.LAZY)
    private Set<Mark> marks;

    @OneToMany(mappedBy = "studying", fetch = FetchType.LAZY)
    private Set<Absence> absences;
}
