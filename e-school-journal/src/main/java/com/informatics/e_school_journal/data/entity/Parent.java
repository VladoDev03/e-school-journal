package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Parent extends BaseEntity {
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Student> children;

    public Parent(String id) {
        super(id);
    }
}
