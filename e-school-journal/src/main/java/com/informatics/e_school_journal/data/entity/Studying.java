package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Studying extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToMany(mappedBy = "studying", fetch = FetchType.LAZY)
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "studying", fetch = FetchType.LAZY)
    private Set<Mark> marks;

    @OneToMany(mappedBy = "studying", fetch = FetchType.LAZY)
    private Set<Absence> absences;
}
