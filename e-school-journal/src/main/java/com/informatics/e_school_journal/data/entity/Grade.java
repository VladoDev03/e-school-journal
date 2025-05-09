package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Grade extends BaseEntity{
    private int grade;
    private String stream;
    @ManyToOne
    private School school;
    @OneToMany(mappedBy = "grade")
    private Set<Teaching> teachings;
}
