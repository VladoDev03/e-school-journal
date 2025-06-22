package com.informatics.e_school_journal.dto.director;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DirectorWithSchoolDto {
    private long id;
    private String keycloakId;
    private long schoolId;
}
