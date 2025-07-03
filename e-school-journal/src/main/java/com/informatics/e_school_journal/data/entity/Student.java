package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Student extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    @ManyToMany(mappedBy = "children", fetch = FetchType.LAZY)
    private Set<Parent> parents;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Mark> marks;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Absence> absences;

    public Student(String id) {
        super(id);
    }
}
