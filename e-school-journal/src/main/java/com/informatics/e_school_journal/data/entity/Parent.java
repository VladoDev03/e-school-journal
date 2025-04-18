package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.Set;

@Entity
public class Parent extends BaseEntity {
    @OneToOne
    private User user;

    @ManyToMany
    private Set<Student> children;
}
