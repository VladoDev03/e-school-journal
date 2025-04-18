package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Teacher extends BaseEntity {
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "teacher")
    private Set<Teaching> teachings;
}
