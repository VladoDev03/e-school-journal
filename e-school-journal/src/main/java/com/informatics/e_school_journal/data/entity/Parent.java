package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
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

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToMany
    private Set<Student> children;
}
