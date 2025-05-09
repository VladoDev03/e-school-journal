package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Column;
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
    @Column(name = "keycloak_id")
    private String keycloakId;

    @OneToMany(mappedBy = "student")
    private Set<Studying> studyingSet;

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;
}
