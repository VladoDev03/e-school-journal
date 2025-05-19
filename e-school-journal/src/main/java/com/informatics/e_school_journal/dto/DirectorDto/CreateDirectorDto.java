package com.informatics.e_school_journal.dto.DirectorDtos;

import com.informatics.e_school_journal.data.entity.School;
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
    private School school;
}
