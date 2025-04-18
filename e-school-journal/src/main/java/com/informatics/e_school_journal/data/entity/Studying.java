package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Studying extends BaseEntity {
    @ManyToOne
    private Student student;

    @ManyToOne
    private Teaching teaching;

    @OneToMany(mappedBy = "studying")
    private Set<Mark> marks;

    @OneToMany(mappedBy = "studying")
    private Set<Absence> absences;
}
