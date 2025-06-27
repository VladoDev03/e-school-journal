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

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Studying> studyings;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "qualification",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;
}
