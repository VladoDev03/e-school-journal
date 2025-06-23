package com.informatics.e_school_journal.data.entity;

import com.informatics.e_school_journal.data.enums.MarkType;
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

    @ManyToOne
    private Studying studying;

    @ManyToOne
    private Student student;
}
