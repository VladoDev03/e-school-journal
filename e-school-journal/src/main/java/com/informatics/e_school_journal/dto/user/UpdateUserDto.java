package com.informatics.e_school_journal.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String email;
}
