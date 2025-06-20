package com.informatics.e_school_journal.dto.parent;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateParentDto {
    private String firstName;
    private String lastName;
    private String phone;
    private Set<Long> childrenIds;
}
