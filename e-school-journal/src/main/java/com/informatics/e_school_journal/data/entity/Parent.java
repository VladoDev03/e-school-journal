package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Parent extends BaseEntity {
    @Column(name = "keycloak_id")
    private String keycloakId;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Student> children;
}
