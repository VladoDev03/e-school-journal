package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Student extends BaseEntity {
    @Column(name = "keycloak_id")
    private String keycloakId;

    @ManyToOne
    private Grade grade;

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;

    @OneToMany(mappedBy = "student")
    private Set<Mark> marks;

    @OneToMany(mappedBy = "student")
    private Set<Absence> absences;
}
