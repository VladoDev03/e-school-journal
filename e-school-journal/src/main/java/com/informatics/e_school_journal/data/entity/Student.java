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

    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    @ManyToMany(mappedBy = "children", fetch = FetchType.LAZY)
    private Set<Parent> parents;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Mark> marks;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Absence> absences;
}
