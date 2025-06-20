package com.informatics.e_school_journal.dto.school;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolDto {
    private long id;
    private String name;
    private String address;
    private long directorId;
}
