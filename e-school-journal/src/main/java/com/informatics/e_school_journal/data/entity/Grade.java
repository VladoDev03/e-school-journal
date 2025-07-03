package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Column(nullable = false)
    @Min(value = 1)
    @Max(value = 12)
    private int grade;

    @Column(nullable = false)
    @Positive
    private int year;

    @Column(nullable = false)
    @Size(min = 1, max = 45, message = "Stream must be between 1 and 45 characters.")
    private String stream;

    @JoinColumn(nullable = false)
    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "grade", fetch = FetchType.LAZY)
    private Set<Studying> studyings;

    @OneToMany(mappedBy = "grade", fetch = FetchType.LAZY)
    private Set<Student> students;
}
