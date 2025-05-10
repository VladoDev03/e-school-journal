package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class School extends BaseEntity {
    private String name;
    private String address;
    @OneToOne(mappedBy = "school")
    private Director director;
    @OneToMany(mappedBy = "school")
    private Set<Grade> grades;
}
