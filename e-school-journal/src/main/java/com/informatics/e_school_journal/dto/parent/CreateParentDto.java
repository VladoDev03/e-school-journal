package com.informatics.e_school_journal.dto.parent;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateParentDto {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private Set<String> childrenIds;
}
