package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Director extends BaseEntity{
    @OneToOne
    private User user;
    @OneToOne
    private School school;
}
