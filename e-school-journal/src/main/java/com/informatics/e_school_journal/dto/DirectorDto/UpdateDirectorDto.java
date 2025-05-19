package com.informatics.e_school_journal.dto.DirectorDto;

import com.informatics.e_school_journal.data.entity.School;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateDirectorDto {
    private String keycloakId;
    private School school;

    UpdateDirectorDto(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    UpdateDirectorDto(School school) {
        this.school = school;
    }
}
