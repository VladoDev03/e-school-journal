package com.informatics.e_school_journal.dto.parent;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateParentRoleDto {
    @NotEmpty
    private Set<String> childrenIds;
}
