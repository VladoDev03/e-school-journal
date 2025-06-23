package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Absence extends BaseEntity{
    private LocalDate date;
    private boolean isExcused;
    @ManyToOne
    private Studying studying;

    @ManyToOne
    private Student student;
}
