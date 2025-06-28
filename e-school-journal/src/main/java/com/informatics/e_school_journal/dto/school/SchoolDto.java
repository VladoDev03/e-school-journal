package com.informatics.e_school_journal.dto.school;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolDto {
    private String id;
    private String name;
    private String address;
    private String directorId;
}
