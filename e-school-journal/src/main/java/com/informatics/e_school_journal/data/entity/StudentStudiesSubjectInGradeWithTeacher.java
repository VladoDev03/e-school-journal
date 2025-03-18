package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class StudentStudiesSubjectInGradeWithTeacher extends BaseEntity{
    @ManyToOne
    private Student student;

    @ManyToOne
    private GradeHasSubjectWithTeacher gradeHasSubjectWithTeacher;

    @OneToMany(mappedBy = "studentStudiesSubjectInGradeWithTeacher")
    private Set<Mark> marks;

    @OneToMany(mappedBy = "studentStudiesSubjectInGradeWithTeacher")
    private Set<Absence> absences;
}
