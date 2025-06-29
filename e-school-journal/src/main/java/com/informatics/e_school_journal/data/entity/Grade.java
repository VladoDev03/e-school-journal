package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "grade", uniqueConstraints = @UniqueConstraint(columnNames = {"grade", "year", "stream", "school_id"}))
public class Grade extends BaseEntity{
    private int grade;
    private int year;
    private String stream;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "grade", fetch = FetchType.LAZY)
    private Set<Studying> studyings;

    @OneToMany(mappedBy = "grade", fetch = FetchType.LAZY)
    private Set<Student> students;
}
