package com.informatics.e_school_journal.dto.schoolDtos;

import com.informatics.e_school_journal.data.entity.Director;
import com.informatics.e_school_journal.data.entity.Grade;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateSchoolDto {
    private String name;
    private String address;
    private Director director;
    private Set<Grade> grades;

    public UpdateSchoolDto(String name, String address) {
        this.name = name;
        this.address = address;
    }

}