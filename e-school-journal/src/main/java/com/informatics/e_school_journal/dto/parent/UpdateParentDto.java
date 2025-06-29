package com.informatics.e_school_journal.dto.parent;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateParentDto {
    private Set<String> childrenIds;
}
