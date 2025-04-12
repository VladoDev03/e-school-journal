package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Subject extends BaseEntity{
    private String name;
    @OneToMany(mappedBy = "subject")
    private Set<Teaching> teachings;
}
