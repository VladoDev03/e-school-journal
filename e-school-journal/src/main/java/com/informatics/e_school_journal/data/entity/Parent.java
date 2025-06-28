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
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Student> children;
}
