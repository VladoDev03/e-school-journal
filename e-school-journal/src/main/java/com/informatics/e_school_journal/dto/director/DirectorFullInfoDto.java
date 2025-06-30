package com.informatics.e_school_journal.dto.director;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorFullInfoDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String schoolId;
    private String schoolName;
}
