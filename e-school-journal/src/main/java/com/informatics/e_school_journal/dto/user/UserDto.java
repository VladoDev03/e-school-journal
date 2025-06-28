package com.informatics.e_school_journal.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
