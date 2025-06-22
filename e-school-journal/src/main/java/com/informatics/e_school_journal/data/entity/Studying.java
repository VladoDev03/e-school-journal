package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
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
    @ManyToOne
    private Grade grade;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "studying")
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "studying")
    private Set<Mark> marks;

    @OneToMany(mappedBy = "studying")
    private Set<Absence> absences;
}
