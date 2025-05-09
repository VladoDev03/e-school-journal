package com.informatics.e_school_journal.dto.DirectorDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateDirectorDto {
    private String keycloakId;
    private long schoolId;
}
