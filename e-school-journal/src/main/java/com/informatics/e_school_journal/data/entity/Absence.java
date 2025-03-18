package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class Absence extends BaseEntity{
    private LocalDate date;
    private boolean isExcused;
    @ManyToOne
    private StudentStudiesSubjectInGradeWithTeacher studentStudiesSubjectInGradeWIthTeacher;


}
