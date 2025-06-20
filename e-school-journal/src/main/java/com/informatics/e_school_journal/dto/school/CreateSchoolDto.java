package com.informatics.e_school_journal.dto.school;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateSchoolDto {
    private String name;
    private String address;
}
