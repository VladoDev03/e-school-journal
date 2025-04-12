package com.informatics.e_school_journal.data.entity;

import com.informatics.e_school_journal.data.enums.MarkType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Mark extends BaseEntity{
    private double mark;
    private MarkType markType;
    @ManyToOne
    private Studying studying;
}
