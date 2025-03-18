package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Grade extends BaseEntity{
    private int grade;
    private String stream;
    @ManyToOne
    private School school;
    @OneToMany(mappedBy = "grade")
    private Set<GradeHasSubjectWithTeacher> gradeHasSubjectWithTeacherSet;
}
