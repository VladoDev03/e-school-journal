package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private Set<Studying> studyings;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private Set<Teacher> teachers;
}