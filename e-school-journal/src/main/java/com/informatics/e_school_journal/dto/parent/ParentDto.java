package com.informatics.e_school_journal.dto.parent;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParentDto {
    private String id;
    @NotEmpty
    private Set<String> childrenIds;
}
