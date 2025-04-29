package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Student extends BaseEntity {
    @OneToMany(mappedBy = "student")
    private Set<Studying> studyingSet;

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;
}
