package com.informatics.e_school_journal.dto.director;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateDirectorDto {
    private String keycloakId;
    private long schoolId;
}
