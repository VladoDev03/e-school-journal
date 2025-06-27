package com.informatics.e_school_journal.data.entity;

import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mark extends BaseEntity {
    private double mark;
    private MarkType markType;
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY)
    private Studying studying;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
