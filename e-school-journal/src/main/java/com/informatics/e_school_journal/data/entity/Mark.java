package com.informatics.e_school_journal.data.entity;

import com.informatics.e_school_journal.data.enums.MarkType;
import com.informatics.e_school_journal.data.enums.Term;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Column(nullable = false)
    @Min(value = 2)
    @Max(value = 6)
    private double mark;

    @Column(nullable = false)
    private MarkType markType;

    @Column(nullable = false)
    private Term term;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Studying studying;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
