package com.informatics.e_school_journal.dto.director;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateSchoolDirectorDto {
    private long directorId;
    private long schoolId;
}
