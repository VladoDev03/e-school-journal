package com.informatics.e_school_journal.dto.parent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentPersonalInfoDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Set<String> childrenIds;
}
