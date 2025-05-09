package com.informatics.e_school_journal.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "subject")
    private Set<Teaching> teachings;
}