package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.relational.core.mapping.MappedCollection;

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

//    private long studyingId;
//    private long parentId;

//    @OneToMany(mappedBy = "student")
//    private Set<Studying> studyingSet;

//    @ManyToMany(mappedBy = "children")
//    private Set<Parent> parents;
}
