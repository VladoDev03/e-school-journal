package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Admin extends BaseEntity {
    @OneToOne
    private User user;
}
