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
public class Teacher extends BaseEntity {
    @Column(name="keycloak_id")
    private String keycloakId;

    @OneToMany(mappedBy = "teacher")
    private Set<Teaching> teachings;
}
