package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Set;

@Entity
public class Student extends BaseEntity{
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private Set<StudentStudiesSubjectInGradeWithTeacher> studentStudiesSubjectInGradeWithTeacherSet;

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;
}
