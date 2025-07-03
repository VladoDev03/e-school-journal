package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class School extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 1, max = 90, message = "Name must be between 1 and 90 characters.")
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToOne(mappedBy = "school", fetch = FetchType.LAZY)
    private Director director;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private Set<Grade> grades;
}
