package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Teaching extends BaseEntity {
    @ManyToOne
    private Grade grade;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "teaching")
    private Set<Studying> studying;

    @OneToMany(mappedBy = "teaching")
    private Set<Schedule> schedules;
}
